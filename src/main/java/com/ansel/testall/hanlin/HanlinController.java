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
		if ("date".equals(orderType)) {
			hl.setCreateDate(new Date());
		} else {
			hl.setThumbUp(1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Hanlin> hanlinList = hanlinService.selectByHanlin(hl);
		for (int i = 0; i < hanlinList.size(); i++) {
			hanlinList.get(i).setCreateDateStr(
					sdf.format(hanlinList.get(i).getCreateDate()));
		}
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
