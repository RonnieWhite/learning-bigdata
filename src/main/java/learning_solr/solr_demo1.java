package main.java.learning_solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class solr_demo1 {
    private final String SOLR_URL = "http://myspark:8080/solr/new_core";

    public static void main(String[] args) throws Exception {
        solr_demo1 solr_demo1 = new solr_demo1();
        solr_demo1.addSolr();
        solr_demo1.querySolr();
//        solr_demo1.deleteSolrById("7");
    }

    public HttpSolrServer createSolrServer() {
        HttpSolrServer solr = null;
        solr = new HttpSolrServer(SOLR_URL);
        return solr;
    }

    public void querySolr() throws Exception {
        HttpSolrServer solrServer = createSolrServer();
        SolrQuery query = new SolrQuery();
        query.set("q", "*:*");
        query.setHighlight(true);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        for (SolrDocument doc : solrDocumentList) {
            System.out.println(doc.get("id") + "-" + doc.get("name") + "-" + doc.get("description"));
        }
        solrServer.close();
    }

    public void addSolr() throws Exception {
        HttpSolrServer solrServer = createSolrServer();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "7");
//        document.addField("name", "钢铁侠");
        document.addField("description", "一般货色");
        solrServer.add(document);
//        Person person = new Person("7", "钢铁侠", "一般货色");
//        solrServer.addBean(person);
        solrServer.commit();
        solrServer.close();
        System.out.println("添加成功！");
    }

    public void deleteSolrById(String id) throws Exception {
        HttpSolrServer solrServer = createSolrServer();
        solrServer.deleteById(id);
        solrServer.commit();
        solrServer.close();
        System.out.println("删除成功！");
    }
}
