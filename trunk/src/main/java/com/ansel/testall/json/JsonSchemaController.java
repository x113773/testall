package com.ansel.testall.json;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class JsonSchemaController {

	@Value("${upload.rootPath}")
	private String rootPath;


	@RequestMapping(value = "/json-schema/validate", method = RequestMethod.GET)
	public Map<String, Object> jsonSchemaValidate(String jsonStr) {
		Map<String, Object> result = new HashMap<String, Object>();

		JsonNode jsonNode = JsonSchemaValidator.getJsonNodeFromString(jsonStr);
		if (jsonNode == null) {
			result.put("success", false);
			result.put("message", "json报文格式错误");
			return result;
		}

		String filePath =  rootPath + "/json-file/json_schema_test.json";
		JsonNode schemaNode = JsonSchemaValidator.getJsonNodeFromFile(filePath);
		if (schemaNode == null) {
			result.put("success", false);
			result.put("message", "json Schema文件不存在，无法校验！");
			return result;
		}
		return JsonSchemaValidator.validateJsonByFgeByJsonNode(jsonNode, schemaNode);
	}

}
