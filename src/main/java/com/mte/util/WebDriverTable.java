package com.mte.util;

import com.mte.base.MteSenseCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebDriverTable {

    private By tabBy = null;
    private List<WebElement> tables = null;
    private WebElement table = null;

    private WebElement thead = null;
    private List<WebElement> tCols = null;

    private WebElement tbody = null;
    private List<WebElement> tRows = null;

    private List<String> colNames = null;

//    private Logger logger = Logger.getLogger(WebDriverTable.class);

    public WebDriverTable(MteSenseCore asCore, By tabFinder) {
        this.tabBy = tabFinder;
        this.tables = asCore.findElements(tabBy);
        if (null == tables || tables.size() == 0) {
            //error
        }
        this.table = tables.get(0);
        this.thead = table.findElements(By.tagName("thead")).get(0);
        if(null == thead){
            //error
        }
        this.tbody = table.findElements(By.tagName("tbody")).get(0);
        if(null == tbody){
            //error
        }

        this.tCols = table.findElements(By.xpath("//thead/tr[2]/td"));
        if (null == tCols || tCols.size() == 0) {
            //error
        }

        this.tRows = table.findElements(By.tagName("tbody")).get(0)
                .findElements(By.tagName("tr"));
        if (null == tRows || tRows.size() == 0) {
            //error
        }

    }


    public WebDriverTable(MteSenseCore asCore, By tabFinder,int index) {
        this.tabBy = tabFinder;
        this.tables = asCore.findElements(tabBy);
        if (null == tables || tables.size() == 0) {
            //error
        }
        this.table = tables.get(0);
        this.thead = table.findElements(By.tagName("thead")).get(0);
        if(null == thead){
            //error
        }
        this.tbody = table.findElements(By.tagName("tbody")).get(0);
        if(null == tbody){
            //error
        }

        this.tCols = table.findElements(By.xpath("//thead/tr['"+index+"']/td"));
        if (null == tCols || tCols.size() == 0) {
            //error
        }

        this.tRows = table.findElements(By.tagName("tbody")).get(0)
                .findElements(By.tagName("tr"));
        if (null == tRows || tRows.size() == 0) {
            //error
        }

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
    public WebElement tableHeader() {
        return this.table.findElements(By.tagName("thead")).get(0);
    }

    /**
     * to get the web table body element.
     *
     * @return the first table body element.
     */
    public WebElement tableBody() {
        return this.table.findElements(By.tagName("tbody")).get(0);
    }

    /**
     * get row count of a webtable.
     *
     * @return the row count of the table.
     */
    public int rowCount() {
        return tRows.size();
    }

    /**
     * get column count of a specified webtable row.
     *
     * @param rowNum
     *            row index of your table to count.
     * @return the column count of the row in table.
     */
    public int colCount(int rowNum) {
        return tRows.get(rowNum - 1).findElements(By.xpath("td")).size();
    }


//    public int colIndex(String colName){
//
//    }
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
    public WebElement childItem(int row, int col, String type, int index) {
        List<WebElement> cells = tRows.get(row - 1).findElements(
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
    public String cellText(int row, int col) {
        return childItem(row, col, "cell", 0).getText();
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
