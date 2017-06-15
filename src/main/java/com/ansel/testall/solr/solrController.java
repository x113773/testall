package com.ansel.testall.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class solrController {
	@RequestMapping(value = "/solr", method = RequestMethod.GET)
	public void getSolr() throws SolrServerException, IOException {

		String urlString = "http://192.168.10.129:8983/solr/mycore";
		SolrClient client = new HttpSolrClient.Builder(urlString).build();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "c");
		UpdateRequest req = new UpdateRequest();
		req.setAction(UpdateRequest.ACTION.COMMIT, false, false);
		req.add(doc);
		UpdateResponse rsp = req.process(client);
		System.out.println(rsp);
	}
	@RequestMapping(value = "/solr2", method = RequestMethod.GET)
	public void getSolr2() throws SolrServerException, IOException {

		String urlString = "http://192.168.10.129:8983/solr/mycore";
		SolrClient client = new HttpSolrClient.Builder(urlString).build();
		client.commit();
	}
}
