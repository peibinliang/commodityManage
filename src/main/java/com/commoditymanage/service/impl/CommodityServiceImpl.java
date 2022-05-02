package com.commoditymanage.service.impl;


import com.commoditymanage.dao.CommodityMapper;
import com.commoditymanage.entity.Commodity;
import com.commoditymanage.entity.User;
import com.commoditymanage.service.CommodityService;
import com.commoditymanage.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    /**
     * 新增商品
     *
     * @param commodity
     * @return
     */
    @Override
    public boolean saveCommodity(Commodity commodity) {
        if (Objects.nonNull(commodityMapper.getCommodityByCommodityName(commodity.getCommodityName()))){
            throw new RuntimeException("商品已存在");
        }
        commodity.setCommodityNo(Utils.generateCommodityNo());
        return commodityMapper.saveCommodity(commodity)>0;
    }

    /**
     * 删除商品
     *
     * @param commodityId
     * @return
     */
    @Override
    public boolean removeCommodity(Integer commodityId) {
        return commodityMapper.removeCommodity(commodityId)>0;
    }

    /**
     * 修改商品
     *
     * @param commodity
     * @return
     */
    @Override
    public boolean modifyCommodity(Commodity commodity) {
        return commodityMapper.modifyCommodity(commodity)>0;
    }

    /**
     * 根据商品Id获取商品详情
     *
     * @param commodityId
     * @return
     */
    @Override
    public Commodity getCommodityByCommodityId(Integer commodityId) {
        return commodityMapper.getCommodityByCommodityId(commodityId);
    }

    /**
     * 商品列表
     *
     * @return
     */
    @Override
    public boolean listCommodity(HttpServletRequest request) {
        List<Commodity> commodityList = new ArrayList<Commodity>(); 	//记录列表
        String search = ""; 								//搜索的内容
        int countShowed = 0;	 							//（要略过的）之前的记录数
        int pageShow = 1; 									//当前页码
        String page = ""; 									//页码链接组
        String msg = "";
        User user = (User)request.getSession().getAttribute("user");
        Integer userRole = user.getUserRole();

        try {
            if (request.getParameter("buttonDelete") != null) { 				//如果单击了删除按钮

                String[] commodityIdArray = request.getParameterValues("commodityId"); 	//获取ID列表
                Integer[] commodityIdList = new Integer[commodityIdArray.length];

                for (int i = 0; i < commodityIdArray.length;i++) {
                    commodityIdList[i] = Integer.parseInt(commodityIdArray[i]);
                }

                if (commodityIdArray != null) {
                    deleteByIdList(request, commodityIdList);						//****删除所选。调用在本类中定义的方法
                }
            }

            String buttonQuery	= request.getParameter("buttonQuery");	//数据查询按钮
            String buttonPage  	= request.getParameter("buttonPage"); 	//页码提交按钮
            String pageInput  	= "1";									//输入的页码

            if (buttonQuery != null) { 									//如果按下了数据查询按钮
                search = request.getParameter("search").trim(); 		//搜索内容
            } else if (buttonPage != null) { 							//如果按下了页码提交按钮
                search 	= request.getParameter("search").trim(); 		//搜索内容
                pageInput	= request.getParameter("pageShow"); 		//页码输入框中的值
            } else { 													//点击了页码链接，或者刚打开此页
                if (request.getParameter("searchUrl") != null) {
                    search = request.getParameter("searchUrl"); 		//不需要进行解码操作，系统会自动解码
                }

                if (request.getParameter("pageUrl") != null) {			//地址栏中的页码
                    pageInput = request.getParameter("pageUrl");
                }
            }

            int countRow = 0;
            if (userRole == 1) {
                countRow = commodityMapper.countCommodity(search);    //****获得记录总数
            }else {
                countRow = commodityMapper.countCommodityOfShop(search);
            }

            int pageSize  = 6;  							//每页6条记录
            int pageCount = 0; 								//预设总页数为0

            if (countRow % pageSize == 0) {					//如果余数为0，即能整除
                pageCount = countRow / pageSize; 			//总页数
            } else {
                pageCount = countRow / pageSize + 1;		//不能整除则加1页。如果除数为小数，将自动去除小数部分得到整数
            }

            try {
                pageShow = Integer.parseInt(pageInput);		//如果是数字，返回字符串对应的整数
            } catch (Exception e) {
                pageShow = 1; 							//如果抛出异常，则取预设值
            }

            if (pageShow < 1) {								//如果当前页码小于1
                pageShow = 1;
            } else if (pageShow > pageCount && pageCount >= 1) { 	//如果当前页码大于总页数，且总页数>=1
                pageShow = pageCount;
            }

            String searchUrl = "";

            if (search.equals("") == false) {
                searchUrl = URLEncoder.encode(search, "UTF-8");		//进行URL编码，以便在地址栏传递
            }

            if (pageShow <= 1) {
                page += "<span style='color:gray;'>首页&ensp;";
                page += "上一页&ensp;</span>";
            } else {
                page += "<a href='?pageUrl=1&searchUrl=" + searchUrl + "'>首页</a>&ensp;";
                page += "<a href='?pageUrl=" + (pageShow - 1) + "&searchUrl=" + searchUrl
                        + "'>上一页</a>&ensp;";
            }

            if (pageShow >= pageCount) {
                page += "<span style='color:gray;'>下一页&ensp;";
                page += "尾页</span>";
            } else {
                page += "<a href='?pageUrl=" + (pageShow + 1) + "&searchUrl=" + searchUrl
                        + "'>下一页</a>&ensp;";
                page += "<a href='?pageUrl=" + pageCount + "&searchUrl=" + searchUrl + "'>尾页</a>";
            }

            page += "&emsp;&emsp;";
            page += "页码：" + pageShow + "/" + pageCount + "&emsp;";
            page += "记录数：" + countRow + "&emsp;&emsp;";

            page += "输入页码:";
            page += "	<input type='text' name='pageShow' value='" + pageShow
                    + "' style='width:40px; text-align:center;'>";
            page += "	<input type='submit' name='buttonPage' value='提交'>&emsp;";

            if (pageShow > 0) {
                countShowed = (pageShow - 1) * pageSize;					//（要略过的）之前的记录数
            }

            if (userRole == 1) {
                commodityList = commodityMapper.listCommodity(search, countShowed, pageSize);    //****获取当前页的记录列表
            }else {
                commodityList = commodityMapper.listCommodityOfShop(search, countShowed, pageSize);
            }

            if (commodityList == null || commodityList.size() == 0) {
                msg = "查无记录。";
                return false;
            }

            return true;

        } catch (Exception e) {
            msg = "系统发生错误。";
            e.printStackTrace();
        } finally {
            if (request.getAttribute("msg") != null) {
                msg = request.getAttribute("msg").toString() + msg;
            }
            request.setAttribute("msg", msg);

            if (request.getSession().getAttribute("msg") != null) {				//如果session中有消息。在详情页删除记录后保存的消息
                msg = request.getSession().getAttribute("msg").toString() + msg;//读取session中的消息
                request.getSession().removeAttribute("msg");					//从session中移除此键值
                request.setAttribute("msg", msg);								//将消息赋值给request
            }

            request.setAttribute("search", search);
            request.setAttribute("countShowed", countShowed);
            request.setAttribute("page", page);
            System.out.println("commodityList==="+commodityList);
            request.setAttribute("commodityList", commodityList);						//****传递对象
        }

        return false;
    }

    public boolean deleteByIdList (HttpServletRequest request, Integer[] commodityIdArray) {		//被本类中的selectAll()方法调用

        String msg = "";

        try {
            for (int i = 0; i < commodityIdArray.length; i++) {
                try {
                    int result = commodityMapper.removeCommodity(commodityIdArray[i]);		//****删除这条记录，本类中的方法

                    if (result == 0) {
                        msg = "删除记录失败！请重试。";
                        return false;
                    }

                } catch (Exception e) {
                    continue;												//略过此项
                }
            }

            msg = "删除记录成功。";
            return true;

        } catch (Exception e) {
            msg += "系统发生错误。";
            e.printStackTrace();
        } finally {
            if (request.getAttribute("msg") != null) {
                msg = request.getAttribute("msg").toString() + msg;
            }
            request.setAttribute("msg", msg);
        }

        return false;
    }

    /**
     * 商品搜索
     *
     * @param searchTerm
     * @return
     */
    @Override
    public List<Commodity> searchCommodity(String searchTerm) {
        return null;
    }
}
