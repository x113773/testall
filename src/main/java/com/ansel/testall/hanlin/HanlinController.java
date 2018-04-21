package com.ansel.testall.hanlin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.helper.SystemUtil;
import com.ansel.testall.mybatis.model.Hanlin;
import com.ansel.testall.mybatis.service.HanlinService;

@RestController
public class HanlinController {

	@Autowired
	HanlinService hanlinService;

	@RequestMapping(value = "/do/hanlin/{orderType}", method = RequestMethod.GET)
	public List<Hanlin> getHanlin(@PathVariable String orderType, String thumbUpMin, String thumbUpMax, String createDateMin, String createDateMax) {
		Map<String, Object> param = new HashMap<String, Object>();
		if ("createDate".equals(orderType)) {
			param.put("createDate", "createDate");
		} else if ("updateDate".equals(orderType)) {
			param.put("updateDate", "updateDate");
		} else if ("thumbUp".equals(orderType)) {
			param.put("thumbUp", "thumbUp");
		} else if ("cthumb".equals(orderType)) {
			param.put("cthumb", "cthumb");
		} else if ("cfirst".equals(orderType)) {
			param.put("cfirst", "cfirst");
		}

		if (!"".equals(thumbUpMin)) {
			param.put("thumbUpMin", Integer.parseInt(thumbUpMin));
		}
		if (!"".equals(thumbUpMax)) {
			param.put("thumbUpMax", Integer.parseInt(thumbUpMax));
		}
		if (!"".equals(createDateMin)) {
			param.put("createDateMin", createDateMin);
		}
		if (!"".equals(createDateMax)) {
			param.put("createDateMax", createDateMax);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		List<Hanlin> hanlinList = hanlinService.selectByHanlin(param);
		for (int i = 0; i < hanlinList.size(); i++) {
			hanlinList.get(i).setCreateDateStr(sdf.format(hanlinList.get(i).getCreateDate()));
			if (hanlinList.get(i).getUpdateDate() != null) {
				hanlinList.get(i).setUpdateDateStr(sdf.format(hanlinList.get(i).getUpdateDate()));
			}
		}
		return hanlinList;
	}

	@RequestMapping(value = "/do/hanlin", method = RequestMethod.POST)
	public String insertHanlin(@RequestBody List<Hanlin> hanlinList) {
		Integer count =0;
		for (Hanlin hl : hanlinList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("url", hl.getUrl());
			List<Hanlin> tmList = hanlinService.selectByHanlin(param);
			if (tmList.size() == 0) {
				hl.setId(SystemUtil.getUuid());
				count++;
				hanlinService.insertSelective(hl);
			} else {
				Hanlin hanlin = tmList.get(0);
				if (hl.getThumbUp() > hanlin.getThumbUp()) {
					long day = (new Date().getTime() - hanlin.getCreateDate().getTime()) / (1000);
					if (day > 60) {
						hanlin.setCthumb(hl.getThumbUp() - hanlin.getThumbUp());
						hanlin.setCfirst(hl.getFirst() - hanlin.getFirst());
					}
					hanlin.setThumbUp(hl.getThumbUp());
					hanlinService.updateByPrimaryKeySelective(hanlin);
				}

			}
		}
		return count.toString();
	}

	@RequestMapping(value = "/qc", method = RequestMethod.GET)
	public List<String> qc() {
		Map<String, Object> param = new HashMap<String, Object>();

		Map<String, Object> tempHanlinMap = new HashMap<String, Object>();
		List<Hanlin> hanlinList = hanlinService.selectByHanlin(param);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < hanlinList.size(); i++) {
			if (tempHanlinMap.containsKey(hanlinList.get(i).getUrl())) {
				Hanlin hl = (Hanlin) tempHanlinMap.get(hanlinList.get(i).getUrl());
				if (hanlinList.get(i).getThumbUp() > hl.getThumbUp()) {
					idList.add(hl.getId());
					tempHanlinMap.put(hanlinList.get(i).getUrl(), hanlinList.get(i));
				} else {
					idList.add(hanlinList.get(i).getId());
				}
			} else {
				tempHanlinMap.put(hanlinList.get(i).getUrl(), hanlinList.get(i));
			}
		}
		for (int i = 0; i < idList.size(); i++) {
			hanlinService.deleteByPrimaryKey(idList.get(i));
		}
		return idList;
	}
}
