package com.ansel.testall.ansible;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ansible")
public class AnsibleSettings {
	private String tomcatPlaybookPath;

	private String weblogicPlaybookPath;

	private String ansibleHostsPath;

	public String getTomcatPlaybookPath() {
		return tomcatPlaybookPath;
	}

	public void setTomcatPlaybookPath(String tomcatPlaybookPath) {
		this.tomcatPlaybookPath = tomcatPlaybookPath;
	}

	public String getWeblogicPlaybookPath() {
		return weblogicPlaybookPath;
	}

	public void setWeblogicPlaybookPath(String weblogicPlaybookPath) {
		this.weblogicPlaybookPath = weblogicPlaybookPath;
	}

	public String getAnsibleHostsPath() {
		return ansibleHostsPath;
	}

	public void setAnsibleHostsPath(String ansibleHostsPath) {
		this.ansibleHostsPath = ansibleHostsPath;
	}

}
