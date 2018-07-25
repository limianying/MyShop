package com.myshop.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.myshop.domain.Product;
import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;


public class AdminUpdateProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Map<String, String[]> properties = request.getParameterMap();
		 * 
		 * Product product = new Product();
		 * 
		 * try { BeanUtils.populate(product, properties); } catch
		 * (IllegalAccessException | InvocationTargetException e) { e.printStackTrace();
		 * }
		 * 
		 * // 封装没有被封装进去的属性
		 * 
		 * // pimage //product.setPimage("products/1/c_0020.jpg"); // pdate Date date =
		 * new Date(); SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String
		 * pdate = sdf.format(date); Date parse = null; try { parse = sdf.parse(pdate);
		 * } catch (ParseException e1) { e1.printStackTrace(); }
		 * 
		 * product.setPdate(parse); // pflag product.setPflag(1);
		 * 
		 * AdminProductService service = new AdminProductServiceImpl(); try {
		 * service.updateProduct(product); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */

		// 文件上传的更新操作
		Product product = new Product();
		AdminProductService service = new AdminProductServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 1.创建硬盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2.创建文件上传核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3.解析请求
			List<FileItem> parseRequest = upload.parseRequest(request);
			
			String path = this.getServletContext().getRealPath("upload");
			String filename = null;
			if (parseRequest != null) {
				for (FileItem item : parseRequest) {
					if (item.isFormField()) {
						// 普通表单项
						String fieldName = item.getFieldName();
						if (fieldName.equals("pid")) {
							String pid = item.getString("UTF-8");
							product = service.findProductUI(pid);
						}

						String value = item.getString("UTF-8");
						map.put(fieldName, value);

					} else {
						// 文件上传
						filename = item.getName();
						if(filename != "") {
							InputStream in = item.getInputStream();
							FileOutputStream out = new FileOutputStream(path + "/" + filename);

							IOUtils.copy(in, out);
							in.close();
							out.close();
							item.delete();
						}
					
					}

				}
			}

			BeanUtils.populate(product, map);
			if (filename != "") {
				product.setPimage("upload/"+filename);
			}

			product.setPdate(new Date());

			product.setPflag(0);

			service.updateProduct(product);

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/adminProductList");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}