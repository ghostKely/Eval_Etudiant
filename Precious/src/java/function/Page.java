/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETU1886-Fanirina
 */
public class Page extends Function{
    int total;
    int limit;
    int currentPage;
    int minRange;
    int maxRange;
    List<Page> pagination;
    String request;

    public Page() {
    }

    public Page(int limit) {
        this.limit = limit;
    }

    public Page(int currentPage, int minRange, int maxRange) {
        this.currentPage = currentPage;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public List<Page> getPagination() {
        return pagination;
    }

    public void setPagination(List<Page> pagination) {
        this.pagination = pagination;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

/* RANKING POUR UN SELECT * AVEC WHERE
    String colonneId = colonne de l'ID de la table dans la database 
    String[][] colonneConditionWhere : 
        - [0] colonneWhere = colonne cible du where dans la database
        - [1] conditionWhere = cible à chercher dans la database */
    public String requestPaginationWhere(int limit, int minRange, int maxRange, String tableName, String colonneId, String[][] colonneConditionWhere) {
        String start = "WITH Selected AS (\n" +
                "    SELECT * FROM "+tableName+"\n" +
                "    WHERE "+colonneId+" BETWEEN "+minRange+" AND "+maxRange+"\n";
        
        String middle = "     AND ";
        for (int i = 0; i < colonneConditionWhere.length; i++) {
            String[] oneColonneCondition = colonneConditionWhere[i];
            if (i != colonneConditionWhere.length-1) {
                middle = "           " + middle + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"' AND \n";
            } else {
                middle = middle + "           " + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"'\n";
            }
        }
        
        String middle1 = "    ORDER BY "+colonneId+"\n"+
                "    LIMIT "+limit+"\n" +
                "),\n" +
                "Additional AS (\n" +
                "    SELECT P.* FROM "+tableName+" P\n" +
                "    WHERE P."+colonneId+" NOT BETWEEN "+minRange+" AND "+maxRange+"\n" +
                "    AND P."+colonneId+" > "+maxRange+"\n";
        
        String middle2 = "     AND ";
        for (int i = 0; i < colonneConditionWhere.length; i++) {
            String[] oneColonneCondition = colonneConditionWhere[i];
            if (i != colonneConditionWhere.length-1) {
                middle2 = "           " + middle2 + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"' AND \n";
            } else {
                middle2 = middle2 + "           " + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"'\n";
            }
        }
        
        String end = "    ORDER BY P."+colonneId+"\n" +
                "    LIMIT ("+limit+" - (SELECT COUNT(*) FROM Selected))\n" +
                ")\n" +
                "SELECT * FROM Selected\n" +
                "UNION ALL\n" +
                "SELECT * FROM Additional\n" +
                "ORDER BY "+colonneId+"\n" +
                "LIMIT "+limit+";";
        
        return start + middle + middle1 + middle2 + end;
    }
    
/* String fonctionGetid = getter ID de la classe
    String colonneId = colonne de l'ID de la table dans la database
    Object object = objet alaina any anaty base */    
    public String requestPagination(int limit, int minRange, int maxRange, String tableName, String colonneId) {
        return "WITH Selected AS (\n" +
                "    SELECT * FROM "+tableName+"\n" +
                "    WHERE "+colonneId+" BETWEEN "+minRange+" AND "+maxRange+"\n" +
                "    ORDER BY "+colonneId+"\n" +
                "    LIMIT "+limit+"\n" +
                "),\n" +
                "Additional AS (\n" +
                "    SELECT P.* FROM "+tableName+" P\n" +
                "    WHERE P."+colonneId+" NOT BETWEEN "+minRange+" AND "+maxRange+"\n" +
                "    AND P."+colonneId+" > "+maxRange+"\n" +
                "    ORDER BY P."+colonneId+"\n" +
                "    LIMIT ("+limit+" - (SELECT COUNT(*) FROM Selected))\n" +
                ")\n" +
                "SELECT * FROM Selected\n" +
                "UNION ALL\n" +
                "SELECT * FROM Additional\n" +
                "ORDER BY "+colonneId+"\n" +
                "LIMIT "+limit+";";
    }

/* RANKING POUR UN SELECT * AVEC WHERE
    String colonneId = colonne de l'ID de la table dans la database 
    String[][] colonneConditionWhere : 
        - [0] colonneWhere = colonne cible du where dans la database
        - [1] conditionWhere = cible à chercher dans la database */       
    public String requestRankingWhere(int limit, String tableName, String colonneId, String[][] colonneConditionWhere) {
        String start = "WITH Ranking AS (\n" +
            "    SELECT "+colonneId+",\n" +
            "           ROW_NUMBER() OVER (ORDER BY "+colonneId+") AS row_num,\n" +
            "           COUNT(*) OVER () AS total_rows \n" +
            "    FROM "+tableName+"\n" +
            "    WHERE\n";
            
        String middle = "";
        
        for (int i = 0; i < colonneConditionWhere.length; i++) {
            String[] oneColonneCondition = colonneConditionWhere[i];
            if (i != colonneConditionWhere.length-1) {
                middle = "           " + middle + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"' AND \n";
            } else {
                middle = middle + "           " + oneColonneCondition[0] + " = '" + oneColonneCondition[1] +"'\n";
            }
        }
        
        String end = ")\n" +
            "SELECT "+colonneId+"\n" +
            "FROM Ranking\n" +
            "WHERE row_num = 1\n" +
            "   OR row_num % "+limit+" = 0\n" +
            "   OR row_num = total_rows\n" +
            "ORDER BY row_num;\n";
        
        return start + middle + end;
    }    
    
/* RANKING POUR UN SELECT * SANS WHERE
    String colonneId = colonne de l'ID de la table dans la database */       
    public String requestRanking(int limit, String tableName, String colonneId) {
        return "WITH Ranking AS (\n" +
            "    SELECT "+colonneId+",\n" +
            "           ROW_NUMBER() OVER (ORDER BY "+colonneId+") AS row_num,\n" +
            "           COUNT(*) OVER () AS total_rows\n" +
            "    FROM "+tableName+"\n" +
            ")\n" +
            "SELECT "+colonneId+"\n" +
            "FROM Ranking\n" +
            "WHERE row_num = 1\n" +
            "   OR row_num % "+limit+" = 0\n" +
            "   OR row_num = total_rows\n" +
            "ORDER BY row_num;\n";
    }

/* LISTE DES ID POUR AVOIR LA RANGE DES ID 10 PAR 10 DANS LA DATABASE */
    public int[] getListeId(Connection connection, int limit, String tableName, String colonneId, String[][] colonneConditionWhere, Object object, String fonctionGetid) throws Exception {
        String requestRanking = "";
//CRAFT DE LA REQUETE POUR LA RANGE DES PAGES
        if (colonneConditionWhere == null) {
            requestRanking = this.requestRanking(limit, tableName, colonneId);
        } else {
            requestRanking = this.requestRankingWhere(limit, tableName, colonneId, colonneConditionWhere);
        }
        
        String fonction = "get"+object.getClass().getSimpleName()+"Id";
        ArrayList<Object> ranking = this.selectWhere(connection, object, tableName, requestRanking, fonction);
        
//TABLEAU POUR LES ID DE TOUTES LES 10
        int[] listeId = new int[ranking.size()];
        for (int i=0; i<ranking.size(); i++) {
            Object oneRanking = ranking.get(i);
            Object objectInstance = object.getClass().getConstructor().newInstance();
            Method fonctionGetObject = objectInstance.getClass().getMethod(fonctionGetid);  //appele de la fonction requise
            int id = (int) fonctionGetObject.invoke(oneRanking);
            listeId[i] = id;
        }
        
        return listeId;
    }
  
    public String requestPagination(Page pageReference, int limit, String tableName, String colonneId, String[][] colonneConditionWhere ) {
        String requestPaginationForPageReference = "";
        if (colonneConditionWhere == null) {
            requestPaginationForPageReference = this.requestPagination(limit, pageReference.getMinRange(), pageReference.getMaxRange(), tableName, colonneId);
        } else {
            requestPaginationForPageReference = this.requestPaginationWhere(limit, pageReference.getMinRange(), pageReference.getMaxRange(), tableName, colonneId, colonneConditionWhere);
        }
        return requestPaginationForPageReference;
    }
    
/* String fonctionGetid = getter ID de la classe
    String colonneId = colonne de l'ID de la table dans la database
    Object object = objet alaina any anaty base */    
    public Object paginationBase(Connection connection, int limit, String tableName, String colonneId, String[][] colonneConditionWhere, Object object, String fonctionGetid) throws Exception {
        int[] listeId = this.getListeId(connection, limit, tableName, colonneId, colonneConditionWhere, object, fonctionGetid);
        System.out.println(listeId.length);
        
//CREATION DES PAGES POUR AVOIR LA RANGE DES PAGES A AFFICHER
//POUR FAIRE LE PRECEDENTE ET LE NEXT       
        String requestPaginationForPageReference = "";
        List<Page> pagination = new ArrayList<Page>();
        for (int i = 0; i < listeId.length; i++) {
            if (i != listeId.length-1 && i != 0) {
                Page page = new Page(i+1, listeId[i]+1, listeId[i+1]);
                page.setRequest(this.requestPagination(page, limit, tableName, colonneId, colonneConditionWhere));
                pagination.add(page);
            } else if (i != listeId.length-1 && i == 0) {
                Page page = new Page(i+1, listeId[i], listeId[i+1]);
                page.setRequest(this.requestPagination(page, limit, tableName, colonneId, colonneConditionWhere));
                pagination.add(page);
            } else if (listeId.length == 1) {
                Page page = new Page(i+1, listeId[i], listeId[i]);
                page.setRequest(this.requestPagination(page, limit, tableName, colonneId, colonneConditionWhere));
                pagination.add(page);
            }
        }
        
        if (pagination.isEmpty() == false) {
            return pagination;
        } else {
            return false;
        }
    }
    
    public int[] getPageNumber(List<Page> pages) {
        int[] pageNumber = new int[pages.size()];
        for (int i = 0; i < pageNumber.length; i++) {
            pageNumber[i] = pages.get(i).getCurrentPage();
        }
        return pageNumber;
    }
}

