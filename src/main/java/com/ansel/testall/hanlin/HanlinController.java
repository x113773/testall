package com.ansel.testall.hanlin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/do/hanlin", method = RequestMethod.GET)
	public List<Hanlin> getHanlin() {
		Hanlin hl = new Hanlin();
		List<Hanlin> hanlinList = hanlinService.selectByHanlin(hl);
		return hanlinList;
	}

	@RequestMapping(value = "/do/hanlin", method = RequestMethod.POST)
	public String insertHanlin(@RequestBody List<Hanlin> hanlinList) {
		for (Hanlin hl : hanlinList) {
			List<Hanlin> tmList = hanlinService.selectByHanlin(hl);
			if (tmList.size() == 0) {
				hl.setId(SystemUtil.getUuid());
				hanlinService.insertSelective(hl);
			}
		}
		return "201";
	}

}
