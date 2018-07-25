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

import com.myshop.domain.Category;
import com.myshop.domain.Product;
import com.myshop.service.AdminProductService;
import com.myshop.service.impl.AdminProductServiceImpl;
import com.myshop.utils.CommonUtils;


public class AdminAddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Map<String, String[]> properties = request.getParameterMap(); Product product
		 * = new Product(); try { BeanUtils.populate(product, properties); } catch
		 * (IllegalAccessException | InvocationTargetException e) { e.printStackTrace();
		 * } // 封装没有被封装进去的属性 // 1.pid product.setPid(UUID.randomUUID().toString()); //
		 * 2.pimage product.setPimage("products/1/c_0020.jpg"); // 3.pdate Date date =
		 * new Date(); SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String
		 * pdate = sdf.format(date); product.setPdate(pdate); // 4.pflag
		 * product.setPflag(1);
		 * 
		 * AdminProductService service = new AdminProductServiceImpl(); try {
		 * service.addProduct(product); } catch (SQLException e) { e.printStackTrace();
		 * } response.sendRedirect(request.getContextPath()+"/adminProductList");
		 */

		// 有图片上传的表单提交
		// 创建硬盘文件项工厂

		Product product = new Product();

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建上传文件核心类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析请求

			List<FileItem> parseRequest = upload.parseRequest(request);
			
			String filename = null;

			String Path = this.getServletContext().getRealPath("upload");
			// 遍历文件项
			if (parseRequest != null) {
				for (FileItem item : parseRequest) {
					if (item.isFormField()) {
						// 1.普通文件项
						String fieldname = item.getFieldName();
						String value = item.getString("UTF-8");

						map.put(fieldname, value);
					} else {
						// 2.文件上传
						filename = item.getName();

						InputStream in = item.getInputStream();
						FileOutputStream out = new FileOutputStream(Path + "/" + filename);
						IOUtils.copy(in, out);
						in.close();
						out.close();
						item.delete();
					}

				}
			}

			BeanUtils.populate(product, map);
			
			product.setPimage("upload/"+filename);

			product.setPid(CommonUtils.getUUID());
			product.setPdate(new Date());
			product.setPflag(1);
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);

			AdminProductService service = new AdminProductServiceImpl();

			service.addProduct(product);

			response.sendRedirect(request.getContextPath() + "/adminProductList");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}