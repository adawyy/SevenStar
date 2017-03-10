package com.mte.util;

import com.mte.base.MteSenseCore;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class WebTable {

    private By tabBy = null;
    private WebElement table = null;
    private List<WebElement> tabRows = null;
    private WebElement tabRows_thead = null;
    private WebElement tabRows_thead_tr = null;
    private List<WebElement> tabRows_thead_td = null;
    private List<WebElement> tabRows_tbody_tr = null;
    private List<WebElement> tables = null;
    private Logger logger = Logger.getLogger(WebTable.class);

    /**
     *
     * @param driver asCore
     * @param tabFinder table的xpath地址  //*[@id='memberadmin']/div[3]/table
     * @param index 1 or 2  ： 基于列名是在thead第一个tr还是第二个tr
     */

    public WebTable(MteSenseCore driver, By tabFinder, int index) {
        this.tabBy = tabFinder;
        this.tables = driver.findElements(tabBy);
        if (null == tables || tables.size() == 0) {
            logger.debug("the table " + tabFinder.toString() + "was not found!");
        }
        this.table = tables.get(0);
        this.tabRows = table.findElements(By.tagName("tr"));
        if (null == tabRows || tabRows.size() == 0) {
            logger.debug("the table " + tabFinder.toString() + "is empty!");
        }
        this.tabRows_thead = table.findElements(By.tagName("thead")).get(0);
        this.tabRows_thead_tr = tabRows_thead.findElements(By.tagName("tr")).get(index-1);
        this.tabRows_thead_td = tabRows_thead_tr.findElements(By.tagName("td"));

        this.tabRows_tbody_tr = table.findElements(By.tagName("tbody")).get(0).findElements(By.tagName("tr"));

    }

    /**
     * to get the whole web table element.
     *
     * @return the table element.
     */
    public WebElement tableElement() {
        return this.table;
    }

    /**
     * to get the web table head element.
     *
     * @return the first table head element.
     */
    public WebElement 获取thead对象() {
        return this.table.findElements(By.tagName("thead")).get(0);
    }

    /**
     * to get the web table body element.
     *
     * @return the first table body element.
     */

    public WebElement 获取tbody对象() {
        return this.table.findElements(By.tagName("tbody")).get(0);
    }

    /**
     * get row count of a webtable.
     *
     * @return the row count of the table.
     */
    public int 获取总记录行数() {
        return tabRows_tbody_tr.size();
    }

    /**
     * get column count of a specified webtable row.
     *
     * @param rowNum
     *            row index of your table to count.
     * @return the column count of the row in table.
     */
    public int 获取总列数(int rowNum) {
        return tabRows.get(rowNum - 1).findElements(By.xpath("td")).size();
    }

    /**
     * 获取所有列名
     * @return 列名的List
     */

    public List<String> 获取所有列名(){
        List<String> result = new ArrayList<String>();
  //      System.out.println(table.findElements(By.tagName("thead")).get(0).findElements(By.tagName("tr")).get(0).getText());
        System.out.println(tabRows_thead_td.size());
        System.out.println(tabRows_thead_td.get(1).getText());
        for (int i = 0; i < this.tabRows_thead_td.size(); i++) {
            System.out.println(tabRows_thead_td.get(i).getText());
            if(tabRows_thead_td.get(i).getText()!=null){
                result.add(tabRows_thead_td.get(i).getText());
            }
        }
        return result;
    }

    /**
     * 根据列名获取到表的列的序列
     * @param 列名 单号。。。
     * @return 列的序列 从1开始
     */

    public int 获取列数(String 列名){
        int result = 0;
        for (int i = 0; i < this.tabRows_thead_td.size(); i++) {
            if(tabRows_thead_td.get(i).getText().equals(列名)){
                result = i;
            }
        }
        return result+1;
    }

    /**
     * 获取到Cell的值
     * @param 行数 5
     * @param 列名 账号，代号。。。
     * @return cell中的文字
     */

    public String 获取单元格值(int 行数, String 列名){
        return 获取单元格值(行数, 获取列数(列名));
    }

    /**
     * 获取单行所有元素
     * @param 第几行 第几行的所有元素
     * @return 所有元素
     */

    public List<WebElement> 获取单行所有元素(int 第几行){
        List<WebElement> row = tabRows_tbody_tr.get(第几行).findElements(By.tagName("<td>"));
        return row;
    }

    /**
     * 获取文字所在行数
     * @param 文字 AT01(大股东)
     * @param 列名 账号
     * @return 文字所在行数
     */

    public int 获取文字所在行数(String 文字,String 列名){
        int result = 0;
        List<WebElement> row = null;
        int 列数 = 获取列数(列名);

        for (int i = 1; i < 获取总记录行数(); i++) {
            row = tabRows_tbody_tr.get(i).findElements(By.tagName("td"));
 //           System.out.println(row.get(列数).getText());
            if(row.get(列数).getText().equals(文字)){
                result = i;
            }
        }
        return result;
    }

    /**
     *
     * @param 条件列名 账号
     * @param 条件文字 AT001
     * @param 目标列名 内容
     * @return 目标元素 修改
     */

    public WebElement 通过条件获取单元格元素(String 条件列名,String 条件文字,String 目标列名){
        WebElement result = null;
        List<WebElement> row = null;
        int 条件列数 = 获取列数(条件列名);
        System.out.println(条件列数);
        int 目标列数 = 获取列数(目标列名);
        System.out.println(目标列数);

        for (int i = 0; i < 获取总记录行数(); i++) {
            row = tabRows_tbody_tr.get(i).findElements(By.tagName("td"));
            System.out.println(row.get(条件列数-1).getText());
            if(row.get(条件列数-1).getText().contains(条件文字)){
                result = 获取单元格对象(i+2,目标列数,"cell",0);
                System.out.println(result.getText());
                break;
            }
        }
        return result;
    }


    /**
     * get the element in the table cell by row and col index.
     *
     * @param row
     *            row index of the table.
     * @param col
     *            column index of the table.
     * @param type
     *            the element type, such as link/button/edit/checkbox/image/list
     *            and so on.
     * @param index
     *            element index in the specified cell, begins with 1.
     * @return the table cell WebElement.
     */
    public WebElement 获取单元格对象(int row, int col, String type, int index) {
        List<WebElement> cells = tabRows.get(row - 1).findElements(
                By.xpath("td"));
        return (type.contains("cell")) ? cells.get(col - 1) : childsGetter(
                cells.get(col - 1), type).get(index - 1);
    }

    /**
     * get the cell text of the table on specified row and column.
     *
     * @param row
     *            row index of the table.
     * @param col
     *            column index of the table.
     * @return the cell text.
     */
    public String 获取单元格值(int row, int col) {
        return 获取单元格对象(row, col, "cell", 0).getText();
    }

    /**
     * button/edit/checkbox are using the same html tag "input", others may be
     * the same.</BR> this method will get the WebElements List accord the user
     * element classes.
     *
     * @param father
     *            the father element to get childs.
     * @param elementClass
     *            link/button/edit/checkbox/image/list and so on.
     * @return the WebElements List.
     */
    private List<WebElement> childsGetter(WebElement father, String elementClass) {
        return father.findElements(By.tagName(elementTagGetter(elementClass)));
    }

    /**
     * get the tag of element by webelement type.
     *
     * @param elementType
     *            link/button/edit/checkbox/image/list and so on.
     */
    private String elementTagGetter(String elementType) {
        if (elementType.toLowerCase().trim().contains("link")) {
            return "a";
        } else if (elementType.toLowerCase().trim().contains("button")) {
            return "input";
        } else if (elementType.toLowerCase().trim().contains("edit")) {
            return "input";
        } else if (elementType.toLowerCase().trim().contains("checkbox")) {
            return "input";
        } else if (elementType.toLowerCase().trim().contains("image")) {
            return "img";
        } else if (elementType.toLowerCase().trim().contains("list")) {
            return "select";
        } else if (elementType.toLowerCase().trim().contains("text")) {
            return "textarea";
        } else {
            return elementType.toLowerCase();
        }
    }
}