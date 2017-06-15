package com.ansel.testall.ansible;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.helper.ExecLinuxCMD;
import com.ansel.testall.helper.FileContentReplaceHelper;
import com.ansel.testall.helper.JsonHelper;

@RestController
public class AnsibleController {
	@RequestMapping(value = "/file/change", method = RequestMethod.GET)
	public void fileChange(HttpServletRequest request) {
		String space4Str = "    ";
		// FileContentReplaceHelper.Modify("/etc/ansible/example.yaml",
		// "#remote_tomcat_path", space4Str +
		// "tomcat: /usr/local/tomcat8/ #remote_tomcat_path");

		// FileContentReplaceHelper.Modify("/etc/ansible/example.yaml",
		// "#local_war_path", space4Str +
		// "war_file: /opt/store/ #local_war_path");

		// FileContentReplaceHelper.Modify("/etc/ansible/hosts",
		// "#host_to_replace",
		// "129web ansible_ssh_host=192.168.10.129 ansible_ssh_pass=\"123qwe\" #host_to_replace");
		Map<String, Object> param = new HashMap<>();
		param.put("local_war_path", "/opt/store/");
		param.put("project_name_arr", new String[] { "gr", "hw2" });
		param.put("tomcat", "/usr/local/tomcat8/");
		String tomcatPlaybookPath = "/etc/ansible/tomcat.yaml";
		
		String jsonParamStr = JsonHelper.object2Json(param);
		StringBuilder sb = new StringBuilder("ansible-playbook ");
		sb.append(tomcatPlaybookPath).append(" --extra-vars '").append(jsonParamStr).append("'");


		String res = ExecLinuxCMD.exec(sb.toString()).toString();
		System.out.println("==========获得值=============");
		System.out.println(res);
	}

	@RequestMapping(value = "/test/weblogic", method = RequestMethod.GET)
	public void testWeblogic(HttpServletRequest request) {
		Map<String, String> param = new HashMap<>();
		param.put("user_name", "weblogic");
		param.put("password", "1234qwer");
		param.put("weblogic_path", "/home/ansel/Oracle/Middleware/Oracle_Home/");
		param.put("local_war_file", "/opt/store/hw.war");
		param.put("domain_name", "base_domain");
		param.put("server_name", "AdminServer");
		param.put("admin_url", "iiop://localhost:7001/");
		param.put("war_file_path", "/home/ansel/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/my_dm/");
		param.put("project_name", "hw");
		String weblogicPlaybookPath = "/etc/ansible/weblogic.yaml";
		
		String jsonParamStr = JsonHelper.object2Json(param);
		
		StringBuilder sb = new StringBuilder("ansible-playbook ");
		sb.append(weblogicPlaybookPath).append(" --extra-vars '").append(jsonParamStr).append("'");
		
//		sb.append("user_name=").append(param.get("user_name"));
//		sb.append(" password=").append(param.get("password"));
//		sb.append(" weblogic_path=").append(param.get("weblogic_path"));
//		sb.append(" local_war_file=").append(param.get("local_war_file"));
//		sb.append(" domain_name=").append(param.get("domain_name"));
//		sb.append(" server_name=").append(param.get("server_name"));
//		sb.append(" admin_url=").append(param.get("admin_url"));
//		sb.append(" war_file_path=").append(param.get("war_file_path"));
//		sb.append(" project_name=").append(param.get("project_name"));
//		sb.append("\"");

		String res = ExecLinuxCMD.exec(sb.toString()).toString();
		System.out.println("==========获得值=============");
		System.out.println(res);
	}
}
