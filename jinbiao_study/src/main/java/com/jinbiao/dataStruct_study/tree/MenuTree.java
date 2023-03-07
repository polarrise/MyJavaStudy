package com.jinbiao.dataStruct_study.tree;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 创建树形结构的类MenuTree。方法getRootNode获取所有根节点，方法builTree将根节点汇总创建树形结构，
 buildChilTree为节点建立次级树并拼接上当前树，递归调用buildChilTree不断为当前树开枝散叶直至找不到新的子树。完成递归，获取树形结构。
 */
public class MenuTree {
      private List<Menu> menuList = new ArrayList<Menu>();
      public MenuTree(List<Menu> menuList) {
                 this.menuList=menuList;
             }

             //建立树形结构
              public List<Menu> builTree(){
                 List<Menu> treeMenus =new  ArrayList<Menu>();
                 List<Menu> rootMenuLists = this.getRootNode();//从菜单数据中获取所有根节点。  (系统管理,订阅区,未知领域)
                  for(Menu menuNode : rootMenuLists) {
                         menuNode=buildChilTree(menuNode);
                         treeMenus.add(menuNode);
                     }
                 return treeMenus;
             }

             //首先从菜单数据中获取所有根节点。
               private List<Menu> getRootNode() {
                   List<Menu> rootMenuLists =new  ArrayList<Menu>();
                   for(Menu menuNode : menuList) {
                       if(menuNode.getParentId().equals("0")) {
                         rootMenuLists.add(menuNode);
                       }
                   }
                 return rootMenuLists;
             }


             //递归,建立子树形结构。实际就是找子节点。
             private Menu buildChilTree(Menu pNode){        //分别构建根节点(系统管理、订阅区、未知领域)的子树。 系统管理->权限管理->密码修改
                 List<Menu> chilMenus =new  ArrayList<Menu>();
                 for(Menu menuNode : menuList) {
      //1.先找"系统管理"是menuList里面谁的parentId,找到了"权限管理"、"系统监控"  2.然后"权限管理"、"系统监控"满足if条件做递归。 找"权限管理"是menuList里面谁的parentId。找到了"密码修改"、"新加用户"。
     //密码修改、新加用户满足if条件。做递归,发现menuList里面没有parentId为密码修改、新加用户的，结束for循环。
    //密码修改没有children,不进行递归,然后chilMenus.add(密码修改),新加用户没有children,不进行递归,chilMenus.add(新加用户)。权限管理.setChildren([{密码修改},{新加用户}])
                         if(menuNode.getParentId().equals(pNode.getId())) {   //如果插入的数据list里面某个节点的父id等于根节点的id，对这个节点做递归。
                             chilMenus.add(buildChilTree(menuNode));
                             }
                     }
                 pNode.setChildren(chilMenus);
                 return pNode;
             }

    public static void main(String[] args) throws JSONException {
                 List<Menu>  menuList= new ArrayList<Menu>();
                 /*插入一些数据*/
                 menuList.add(new Menu("GN001D000","0","系统管理","/admin","Y"));                 //根节点
                 menuList.add(new Menu("GN001D100","GN001D000","权限管理","/admin","Y"));        //一级节点
                 menuList.add(new Menu("GN001D110","GN001D100","密码修改","/admin","Y"));       //二级节点
                 menuList.add(new Menu("GN001D120","GN001D100","新加用户","/admin","Y"));      //二级节点
                 menuList.add(new Menu("GN001D200","GN001D000","系统监控","/admin","Y"));      //一级节点
                 menuList.add(new Menu("GN001D210","GN001D200","在线用户","/admin","Y"));     //二级节点
                 menuList.add(new Menu("GN002D000","0","订阅区","/admin","Y"));              //根节点
                 menuList.add(new Menu("GN003D000","0","未知领域","/admin","Y"));            //根节点
                 /*让我们创建树*/
                 MenuTree menuTree =new MenuTree(menuList);
                 menuList=menuTree.builTree();
                 /*转为json看看效果*/
                 JSONArray json = new JSONArray();
                 JSONObject jo = new JSONObject();
                 jo.putOpt("menuList",menuList);  //JSON--List集合转换成JSON对象
                 //String jsonOutput= JSONArray.fromObject(menuList).toString();
                 System.out.println(jo);
    }

}
