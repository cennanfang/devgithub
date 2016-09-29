package com.redbird.wehelp.util;

import java.util.List;  

/** 
 * ��װ��ҳ��Ϣ 
 * @author Administrator 
 * 
 */  
public class PageModel<T> {  
  
    //�����  
    private List<T> list;  
      
    //��ѯ��¼��  
    private int totalRecords;  
      
    //ÿҳ����������  
    private int pageSize;  
      
    //�ڼ�ҳ  
    private int pageNo;  
      
    /** 
     * ��ҳ�� 
     * @return 
     */  
    public int getTotalPages() {  
        return (totalRecords + pageSize - 1) / pageSize;  
    }  
      
    /** 
     * ȡ����ҳ 
     * @return 
     */  
    public int getTopPageNo() {  
        return 1;  
    }  
      
    /** 
     * ��һҳ 
     * @return 
     */  
    public int getPreviousPageNo() {  
        if (pageNo <= 1) {  
            return 1;  
        }  
        return pageNo - 1;  
    }  
      
    /** 
     * ��һҳ 
     * @return 
     */  
    public int getNextPageNo() {  
        if (pageNo >= getBottomPageNo()) {  
            return getBottomPageNo();  
        }  
        return pageNo + 1;    
    }  
      
    /** 
     * ȡ��βҳ 
     * @return 
     */  
    public int getBottomPageNo() {  
        return getTotalPages();  
    }  
      
    public List<T> getList() {  
        return list;  
    }  
  
    public void setList(List<T> list) {  
        this.list = list;  
    }  
  
    public int getTotalRecords() {  
        return totalRecords;  
    }  
  
    public void setTotalRecords(int totalRecords) {  
        this.totalRecords = totalRecords;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(int pageNo) {  
        this.pageNo = pageNo;  
    }  
}  
