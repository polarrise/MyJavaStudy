package jinbiao.dataStruct_study.tree;

import lombok.Data;

import java.util.List;

/**
 递归生成一个如图的菜单，编写两个类数据模型Menu、和创建树形的MenuTree。通过以下过程实现：
 　　　　1.首先从菜单数据中获取所有根节点。
 　　　　2.为根节点建立次级子树并拼接上。
 　　　　3.递归为子节点建立次级子树并接上，直至为末端节点拼接上空的“树”。
 */
@Data
public class Menu {
    private String id;
      private String parentId;
      private String text;
      private String url;
      private String yxbz;
     private List<Menu> children;
     public Menu(String id,String parentId,String text,String url,String yxbz) {
                 this.id=id;
                this.parentId=parentId;
                 this.text=text;
                 this.url=url;
                 this.yxbz=yxbz;
             }


}
