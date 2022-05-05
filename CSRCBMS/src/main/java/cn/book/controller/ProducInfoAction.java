package cn.book.controller;

import com.github.pagehelper.PageInfo;
import cn.book.pojo.ProductInfo;
import cn.book.pojo.vo.ProductinfoVo;
import cn.book.service.ProductInfoService;
import cn.book.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/prod")
public class ProducInfoAction {

    public static final int PAGE_Size=5;

    String saveFileName="";
    /**
     * 界面层一定有业务逻辑层
     */
    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/getAll.action")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    /**
     * 分页处理
     * @param request
     * @return
     */
    @RequestMapping("/spilt.action")
    public String spilt(HttpServletRequest request){

        PageInfo info = null;
        Object vo = request.getSession().getAttribute("prodvo");
        if (vo != null){
            info = productInfoService.splitPageVo((ProductinfoVo) vo,PAGE_Size);
            request.getSession().removeAttribute("prodvo");
        }else {
            info = productInfoService.spiltPage(1,PAGE_Size);
        }
        request.setAttribute("info",info);
        return "product";
    }


    /**
     * ajax分页翻页处理
     */
    @ResponseBody
    @RequestMapping("/ajaxSplit.action")
    public void ajaxSpilt(ProductinfoVo vo, HttpSession session){
        //取得配置数据
        PageInfo info = productInfoService.splitPageVo(vo,PAGE_Size);
        session.setAttribute("info",info);
    }


    @RequestMapping("/toAdd.action")
    public String toAdd(){
        return "addproduct";
    }


    /**
     * 异步ajax文件上传处理
     * 前两部为了获取路径和文件名 然后第三步转存到拼起来的路径
     */
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //提前生成文件名UUID+上传图片的后缀
        String saveFileName = cn.book.utils.FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中的图片存储路径
        String path = request.getServletContext().getRealPath("/img_big");
        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回客户端JSON对象，封装图片的路径
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }


    /**
     * 新增商品
     * @param info
     * @param request
     * @return
     */
    @RequestMapping("/save.action")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0){
            //增加成功
            request.setAttribute("msg","增加成功");
        }else {
            //增加失败
            request.setAttribute("msg","增加失败");
        }
        return "redirect:/prod/spilt.action";
    }

    @RequestMapping("/one.action")
    public String oneId(int pid,ProductinfoVo vo, Model model,HttpSession session) {
        ProductInfo info = productInfoService.getByTd(pid);
        model.addAttribute("prod",info);
        //将多条件及页码放入session中，跟新结束后读取条件和页码
        session.setAttribute("prodvo",vo);
        return "update";
    }


    /**
     * 跟新商品
     */
    @RequestMapping("/update.action")
    public String update(ProductInfo info,HttpServletRequest request){
        //因为 ajax的异步图片上传,如果有上传过,
        //则 saveFileName里有上传来的图片的名称,
        //如果没有使用异步 ajax上传过图片,则 saveFileName="";
        //实体类 info使用隐藏表单域提供上来的 pImage原始图片的名称
        //说明 修改过图片
        if (saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0){
            request.setAttribute("msg","跟新成功");
        }else {
            request.setAttribute("msg","跟新失败");
        }
        saveFileName="";
        return "redirect:/prod/spilt.action";
    }


    /**
     * 删除商品
     */
    @RequestMapping("/delete.action")
    public String delete(int pid,ProductinfoVo vo,HttpServletRequest request){
        int num = -1;

        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0){
            request.setAttribute("msg","删除成功");
            request.getSession().setAttribute("deleteProdVo",vo);
        }else {
            request.setAttribute("msg","删除失败");
        }

        //删除结束后跳转到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }

    /**
     * 删除商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit.action",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("deleteProdVo");
        if ( vo != null){
            info = productInfoService.splitPageVo((ProductinfoVo) vo,PAGE_Size);
        }else {
            info = productInfoService.spiltPage(1,PAGE_Size);
        }
        //取得第一页的数据
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    @RequestMapping("/deleteBatch.action")
    public String deleteBatch(String pids,HttpServletRequest request){
        //上传字符串变成数组
        String []ps = pids.split(",");

        int num = productInfoService.deleteBeach(ps);
        try {
            if (num > 0){
                request.setAttribute("msg","批量删除成功");
            }else {
                request.setAttribute("msg","批量删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","商品不可删除");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }

    /**
     * 多条件查询
     */
    @ResponseBody
    @RequestMapping("/condition.action")
    public void condition(ProductinfoVo vo,HttpSession session){
        List<ProductInfo> list = productInfoService.selectCondition(vo);
        session.setAttribute("list",list);
    }
}
