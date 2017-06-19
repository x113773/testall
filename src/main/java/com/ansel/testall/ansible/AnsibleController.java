package com.ansel.testall.ansible;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.helper.ExecLinuxCMD;
import com.ansel.testall.helper.FileContentReplaceHelper;
import com.ansel.testall.helper.JsonHelper;

@RestController
public class AnsibleController {
	@Autowired
	private AnsibleSettings ansibleSettings;

	@RequestMapping(value = "/test/tomcat", method = RequestMethod.GET)
	public void testTomcat(HttpServletRequest request) {
		Map<String, Object> ansibleSettingsMap = new HashMap<>();
		ansibleSettingsMap.put("ansible_ssh_hosts_path", ansibleSettings.getAnsibleHostsPath());
		ansibleSettingsMap.put("ansible_ssh_host", "192.168.10.129");
		ansibleSettingsMap.put("ansible_ssh_user", "root");
		ansibleSettingsMap.put("ansible_ssh_pass", "123qwe");
		
		Map<String, Object> playbookParamMap = new HashMap<>();
		playbookParamMap.put("local_war_path", "/opt/store/");
		playbookParamMap.put("project_name_arr", new String[] { "gr", "hw2" });
		playbookParamMap.put("tomcat", "/usr/local/tomcat8/");
		
		AnsibleHelper.doDeploy(ansibleSettingsMap, playbookParamMap, ansibleSettings.getTomcatPlaybookPath());
	}

	@RequestMapping(value = "/test/weblogic", method = RequestMethod.GET)
	public void testWeblogic(HttpServletRequest request) {
		Map<String, Object> ansibleSettingsMap = new HashMap<>();
		ansibleSettingsMap.put("ansible_ssh_hosts_path", ansibleSettings.getAnsibleHostsPath());
		ansibleSettingsMap.put("ansible_ssh_host", "192.168.10.129");
		ansibleSettingsMap.put("ansible_ssh_user", "root");
		ansibleSettingsMap.put("ansible_ssh_pass", "123qwe");
		
		Map<String, Object> playbookParamMap = new HashMap<>();
		playbookParamMap.put("user_name", "weblogic");
		playbookParamMap.put("password", "1234qwer");
		playbookParamMap.put("weblogic_path", "/home/ansel/Oracle/Middleware/Oracle_Home/");
		playbookParamMap.put("local_war_file", "/opt/store/hw.war");
		playbookParamMap.put("domain_name", "base_domain");
		playbookParamMap.put("server_name", "AdminServer");
		playbookParamMap.put("admin_url", "iiop://localhost:7001/");
		playbookParamMap.put("war_file_path", "/home/ansel/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/my_dm/");
		playbookParamMap.put("project_name", "hw");
		
		AnsibleHelper.doDeploy(ansibleSettingsMap, playbookParamMap, ansibleSettings.getWeblogicPlaybookPath());
	}
}
