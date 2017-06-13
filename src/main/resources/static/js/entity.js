/**
 * Entity
 */
function entity(parantId) {
	this.children = $("#" + parantId).find("*");
	this.getJsonFromControls = function(append) {
		var entity = new Object();
		this.children.each(function(i, control) {
			if ($(control).attr("id") != undefined && $(control).attr("name") != undefined && $(control).attr("id") == $(control).attr("name")) {
				switch ($(control).prop("tagName")) {
				default:
					if (control.value != null) {
						eval("entity." + $(control).attr("id") + " = '" + control.value + "'");
					}
				}
			}
		});
		if (append != null) {
			for (key in append) {
				entity[key] = append[key];
			}
		}
		return entity;
	}
	// 通过json设值 input适用
	this.setControlsByJson = function(json, status) {
		if (typeof (json) == "string") {
			json = JSON.parse(json);
		}
		this.children.each(function(i, control) {
			if ($(control).attr("id") != undefined && $(control).attr("name") != undefined && $(control).attr("id") == $(control).attr("name")) {
				var value = eval("json." + control.id);
				// 设置控件状态
				switch (status) {
				case "readonly":
					$(control).prop("readonly", "readonly");
					break;
				case "disabled":
					$(control).prop("disabled", "disabled");
					break;
				}
				switch ($(control).prop("tagName")) {
				case "TEXTAREA":
				case "INPUT":
					$(control).val(value);
					break;
				case "IMG":
					$(control).attr("src", value);
					break;
				case "SELECT":
					$(control).find("[value=" + value + "]").prop("selected", "selected");
					break;
				default:
					$(control).text(value);
				}
			}
		});
	}

}