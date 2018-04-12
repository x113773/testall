package com.ansel.testall.hanlin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public List<Hanlin> getHanlin(@PathVariable String orderType) {
		Hanlin hl = new Hanlin();
		if ("createDate".equals(orderType)) {
			hl.setCreateDate(new Date());
		} else if ("updateDate".equals(orderType)) {
			hl.setUpdateDate(new Date());
		} else if ("thumbUp".equals(orderType)) {
			hl.setThumbUp(1);
		} else if ("cthumb".equals(orderType)) {
			hl.setCthumb(1);
		} else if ("cfirst".equals(orderType)) {
			hl.setCfirst(1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		List<Hanlin> hanlinList = hanlinService.selectByHanlin(hl);
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
		for (Hanlin hl : hanlinList) {
			Hanlin temp = new Hanlin();
			temp.setUrl(hl.getUrl());
			List<Hanlin> tmList = hanlinService.selectByHanlin(temp);
			if (tmList.size() == 0) {
				hl.setId(SystemUtil.getUuid());
				hanlinService.insertSelective(hl);
			} else {
				Hanlin hanlin = tmList.get(0);
				hanlin.setCthumb(hl.getThumbUp() - hanlin.getThumbUp());
				hanlin.setCfirst(hl.getFirst() - hanlin.getFirst());
				hanlin.setThumbUp(hl.getThumbUp());
				hanlinService.updateByPrimaryKeySelective(hanlin);
			}
		}
		return "201";
	}

}
