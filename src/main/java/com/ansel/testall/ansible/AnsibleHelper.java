package com.ansel.testall.ansible;

import java.util.Map;

import com.ansel.testall.helper.ExecLinuxCMD;
import com.ansel.testall.helper.FileContentReplaceHelper;
import com.ansel.testall.helper.JsonHelper;

public class AnsibleHelper {
	/**
	 * 完成Tomcat或weblogic的项目自动部署（tomcat支持多个项目同时部署，weblogic一次只能部署一个项目）
	 * 
	 * @param ansibleSettingsMap
	 *            该参数需要提供如下几个键值对：
	 *            ansibleSettingsMap.put("ansible_ssh_hosts_path"
	 *            ,"本机hosts文件路径，带hosts文件名");
	 *            ansibleSettingsMap.put("ansible_ssh_host", "目标主机ip地址");
	 *            ansibleSettingsMap.put("ansible_ssh_user", "目标主机用户名");
	 *            ansibleSettingsMap.put("ansible_ssh_pass", "目标主机用户密码");
	 * @param playbookParamMap
	 *            若为tomcat部署，该参数需要提供如下几个键值对：
	 *            playbookParamMap.put("local_war_path", "本机要部署的war包路径，不带文件名");
	 *            playbookParamMap.put("project_name_arr",
	 *            字符串数组格式的项目（war包）名，不带.war后缀});
	 *            playbookParamMap.put("tomcat","目标主机tomcat路径，到tomcat/主目录为止");
	 *            -----------------------------------------------------------------
	 *            若为weblogic部署，该参数需要提供如下几个键值对：
	 *            playbookParamMap.put("user_name","目标主机weblogic用户名");
	 *            playbookParamMap.put("password", "目标主机weblogic密码");
	 *            playbookParamMap.put("weblogic_path",
	 *            "目标主机weblogic路径，到Oracle_Home/为止");
	 *            playbookParamMap.put("local_war_file", "本机要部署的war包路径，带文件名");
	 *            playbookParamMap.put("domain_name", "weblogic域名");
	 *            playbookParamMap.put("server_name", "weblogic服务名称");
	 *            playbookParamMap.put("admin_url", "weblogic服务的地址，需使用iiop协议" );
	 *            playbookParamMap.put("war_file_path", "目标主机war包的存储路径" );
	 *            playbookParamMap.put("project_name",
	 *            "项目名称（即war包名称，不带.war后缀）");
	 * @param playbookPath
	 *            ansible tomcat或weblogic的playbook路径，带文件名
	 */
	public static void doDeploy(Map<String, Object> ansibleSettingsMap, Map<String, Object> playbookParamMap, String playbookPath) {
		setAnisbleHosts(ansibleSettingsMap);
		String res = doAnisblePlaybook(playbookParamMap, playbookPath);
		System.out.println("==========输出=============");
		System.out.println(res);
	}

	private static void setAnisbleHosts(Map<String, Object> ansibleSettingsMap) {
		StringBuilder hostsSb = new StringBuilder("host1 ");
		hostsSb.append("ansible_ssh_host=").append(ansibleSettingsMap.get("ansible_ssh_host"));
		hostsSb.append(" ansible_ssh_user=").append(ansibleSettingsMap.get("ansible_ssh_user"));
		hostsSb.append(" ansible_ssh_pass=").append(ansibleSettingsMap.get("ansible_ssh_pass"));
		hostsSb.append(" #host_to_replace");
		FileContentReplaceHelper.Modify("/etc/ansible/hosts", "#host_to_replace", hostsSb.toString());
	}

	private static String doAnisblePlaybook(Map<String, Object> playbookParamMap, String playbookPath) {
		String playbookParamStr = JsonHelper.object2Json(playbookParamMap);
		StringBuilder playbookSb = new StringBuilder("ansible-playbook ");
		playbookSb.append(playbookPath).append(" --extra-vars '").append(playbookParamStr).append("'");
		return ExecLinuxCMD.exec(playbookSb.toString()).toString();
	}
}
