var tunList = document.getElementsByClassName("thumb-up-num");
var res = [];
for (var i = 0; i < tunList.length; i++) {
	if (5000 >= tunList[i].innerText && tunList[i].innerText >= 11) {
		var x = document.getElementsByClassName("thumb-up-num")[i];

		while (x.className.indexOf('ans-item') < 0) {
			x = x.parentNode;
			if (x.className == null)
				continue;
		}
		var tt = x.getElementsByClassName("title")[0].firstElementChild;
		if (tt.href != undefined) {
			res.push("-" + tt.innerText + "*" + tt.href + "*"
					+ tunList[i].innerText + "-");
			// res.push(tt.href);
		}

	}

}

var aa = "";
for (var i = 0; i < res.length; i++) {
	aa += res[i];
}
console.log(aa);
var content = aa;
var contentArr = content.split("-");
var hanlinList = [];
for (var i = 0; i < contentArr.length; i++) {
	if (contentArr[i].indexOf("http") > -1) {
		var tempArr = contentArr[i].split("*");
		var hanlin = new Object();
		hanlin.title = tempArr[0];
		if (tempArr[1].indexOf("https") < 0) {
			tempArr[1] = tempArr[1].replace("http", "https");
		}
		hanlin.url = tempArr[1].substr(0, 55);
		hanlin.first = tempArr[2];
		hanlin.thumbUp = tempArr[2];
		hanlin.cfirst = 0;
		hanlin.cthumb = 0;
		hanlinList.push(hanlin);
	}
}


var myXMLHttpRequest = null;
if (window.ActiveXObject) {
	myXMLHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
} else {
	myXMLHttpRequest = new XMLHttpRequest();
}
var url = "https://localhost:8443/do/hanlin";
var data = JSON.stringify(hanlinList); // val一般是从某个html元素中取出的value值
myXMLHttpRequest.open("post", url, true);
myXMLHttpRequest.setRequestHeader("Content-Type", "application/json");
myXMLHttpRequest.onreadystatechange = function() {
	if (myXMLHttpRequest.readyState == 4 && myXMLHttpRequest.status == 200) {
		var res = myXMLHttpRequest.responseText;
		var jsonObj = eval("(" + res + ")");
		console.log(" 本次：" + hanlinList.length +" 实际："+jsonObj);
		console.log(" ");console.log(" ");console.log(" ");console.log(" ");console.log(" ");console.log(" ");console.log(" ");console.log(" ");
		// 接下来就可以使用jsonObj这个json对象取出其中的属性值，做一些修改html元素value值等操作了。
	}
}
myXMLHttpRequest.send(data);