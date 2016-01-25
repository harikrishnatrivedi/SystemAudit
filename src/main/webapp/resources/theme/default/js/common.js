function securityCheckAll() {
	var isValid = true;
	var flag = true;
	textArr = document.getElementsByTagName("INPUT");
	for (var i = 0; i < textArr.length; i++) {
		flag = securityCheck(textArr[i]);
		if (flag == false)
			isValid = false
	}
	taArr = document.getElementsByTagName("TEXTAREA");
	for (i = 0; i < taArr.length; i++) {
		flag = securityCheck(taArr[i]);
		if (flag == false)
			isValid = false
	}
	if (isValid == false) {
		alert("Please enter Valid Values");
		return false
	}
}
function fn_isNumericValue(a) {
	var s;
	s = a.length;
	for (var i = 0; i < s; i++)
		if ("1234567890.".indexOf(a.charAt(i)) == -1)
			return false;
	return true
}
function fn_isAlphaValue(a) {
	var s;
	s = a.length;
	for (var i = 0; i < s; i++)
		if ("1234567890!@#$%^&*()+-".indexOf(a.charAt(i)) != -1)
			return false;
	return true
}
function searchcheck(strtag) {
	var isValid = true;
	var flag = true;
	for (var i = 0; i < textArr.length; i++) {
		strVal = strtag.value;
		var regexpforMETACHAR1 = /(\%27)|(&#32)|(u0027)|(\?)|(\+)|(\-)|(\~)|(\!)|(\*)|(\')|(\-\-)/i;
		if (regexpforMETACHAR1.test(strVal))
			return false;
		else
			return true
	}
}
function isNull(e) {
	if (e.value == "" || e.selectedIndex == 0)
		return true
}
function securityCheck(formElement) {
	var str = formElement.value;
	if (checkForCSS(str) && sqlInjection(str))
		return true;
	else
		return false
}
function checkForCSS(strVal) {
	var regexpforHTMLTag1 = /(<|&#60|u003C)\s*(\S+)\s*[^>]*\s*(>|&#62|u003E)(.*)(<|&#60|U003C)\/\s*\2\s*(>|&#62|u003E)/i;
	var regexpforHTMLTag2 = /(<|&#60|u003C)\s*(\S+)\s*([^>]*)\s*(>|&#62|u003E)/i;
	var regexpforXMLTag = /((<|&#60|u003C).[^(><.)]+(>|&#62|u003E))/i;
	var regexpForEqualVal = /(\s*\w+\s*)=\1/i;
	if (regexpforHTMLTag2.test(strVal) || regexpforHTMLTag1.test(strVal)
			|| regexpforXMLTag.test(strVal) || regexpForEqualVal.test(strVal)
			|| !sqlInjection(strVal))
		return false;
	else
		return true
}
function sqlInjection(strVal) {
	var regexpforMETACHAR1 = /(\%27)|(&#32)|(u0027)|(\')|(\-\-)/i;
	var regexpforMETACHAR2 = /((\%3D)|(&#61)|(u003D)|(=))[^\n]*((\%27)|(&#32)|(u0027)|(\')|(\-\-)|(\%3B)|(&#59)|(u003B)|(;))/i;
	var regexpforORclause = /\w*((\%27)|(&#32)|(u0027)|(\'))(\s*)((\%6F)|(&#111)|(u006F)|o|(\%4F)|(&#79)|(u004F))((\%72)|(&#114)|(u0072)|r|(\%52)|(&#82)|(u0052))/i;
	var regexpforSQLwords = /((\%27)|(&#32)|(u0027)|(\'))(\s*)(union|select|insert|update|delete|drop)/i;
	var regexpforMsSQL = /exec(\s|\+)+(s|x)p\w+/i;
	if (regexpforMETACHAR1.test(strVal) || regexpforMETACHAR2.test(strVal)
			|| regexpforORclause.test(strVal) || regexpforSQLwords.test(strVal)
			|| regexpforMsSQL.test(strVal))
		return false;
	else
		return true
}

function isAlphaNumeric(formElement) {
	var len, str, str1, i;
	len = formElement.value.length;
	str = formElement.value;
	str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-,./&!#() \n";
	if (securityCheck(formElement)) {
		for (i = 0; i < len; i++)
			if (str1.indexOf(str.charAt(i)) == -1) {
				alert("Enter Alphabets or Numbers in the field");
				formElement.value = "";
				formElement.focus();
				return false
			}
		return true
	} else {
		alert("Special characters not allowed. Please Enter valid data.");
		formElement.value = "";
		formElement.focus();
		return false
	}
}

function isNumeric(formElement) {
	var len, str, str1, i;
	len = formElement.value.length;
	str = formElement.value;
	str1 = "0123456789";
	if (securityCheck(formElement)) {
		for (i = 0; i < len; i++)
			if (str1.indexOf(str.charAt(i)) == -1) {
				alert("Enter Numeric Data in this field");
				formElement.value = "";
				formElement.focus();
				return false
			}
		return true
	} else {
		alert("Special characters not allowed. Please Enter valid data.");
		formElement.value = "";
		formElement.focus();
		return false
	}
}

function isNumeric_DecimalNegative(formElement) {
	var len, str, str1, i, dec_counter, newi;
	len = formElement.value.length;
	newi = 0;
	final_val = "";
	str = formElement.value;
	if (securityCheck(formElement)) {
		if (str.charAt(i) == "-") {
			str = str.substr(1, len);
			newi = 1
		}
		str1 = "0123456789.";
		dec_counter = 0;
		dec_set = 0;
		after_dec = 0;
		for (i = newi; i < len; i++) {
			if (dec_set == 1) {
				after_dec++;
				if (after_dec > 2) {
					alert("Only two digit is allowed after decimal point.");
					formElement.value = "";
					formElement.focus();
					return true
				}
			}
			if (str.charAt(i) == ".") {
				if (dec_counter > 0) {
					alert("Only one Decimal Point is allowed in the number.");
					formElement.value = "";
					formElement.focus();
					return false
				} else
					dec_counter++;
				dec_set = 1
			}
			if (str1.indexOf(str.charAt(i)) == -1) {
				alert("Enter Numeric Data in this field");
				formElement.value = "";
				formElement.focus();
				return false
			}
		}
		if (dec_set == 0)
			if (len > 11) {
				alert("The integer part of the no. cannot be longer than 11 digits.");
				formElement.value = "";
				formElement.focus();
				return false
			}
		return true
	} else {
		alert("Special characters not allowed. Please Enter valid data.");
		formElement.value = "";
		formElement.focus();
		return false
	}
}

function onlyAlphaNumeric(formElement) {
	var len, str, str1, i;
	len = formElement.value.length;
	str = formElement.value;
	str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	if (securityCheck(formElement)) {
		for (i = 0; i < len; i++)
			if (str1.indexOf(str.charAt(i)) == -1) {
				alert("Enter Alphabets or Numbers in the field");
				formElement.value = "";
				formElement.focus();
				return false
			}
		return true
	} else {
		alert("Special characters not allowed. Please Enter valid data.");
		formElement.value = "";
		formElement.focus();
		return false
	}
}
function CSTAlphaNumeric(formElement) {
	var len, str, str1, i;
	len = formElement.value.length;
	str = formElement.value;
	str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()[]-_\\\/";
	if (securityCheck(formElement)) {
		for (i = 0; i < len; i++)
			if (str1.indexOf(str.charAt(i)) == -1) {
				alert("Only Alphanumeric values and ( ) [ ] \\ \/ _ - are allowed.");
				formElement.value = "";
				formElement.focus();
				return false
			}
		return true
	} else {
		alert("Only Alphanumeric values and ( ) [ ] \\ \/ _ - are allowed.");
		formElement.value = "";
		formElement.focus();
		return false
	}
}
function chkDec(txt, digit, dec) {
	var patt = "^\\d{1," + digit + "}(\\.\\d\\d{0," + (parseInt(dec) - 1)
			+ "})\?\$";
	var reg = new RegExp(patt);
	if (!txt.value.match(reg) && txt.value.length != 0) {
		alert("Only Numbers upto length " + (parseInt(digit) + parseInt(dec))
				+ " including " + dec + " decimal places allowed");
		txt.value = "";
	}
}

// trim.js
function TrimAll() {
	textArr = document.getElementsByTagName("INPUT");
	for (var i = 0; i < textArr.length; i++) {
		field_type = textArr[i].type.toLowerCase();
		if (field_type != "button")
			Trim(textArr[i]);
	}
	taArr = document.getElementsByTagName("TEXTAREA");
	for (i = 0; i < taArr.length; i++)
		Trim(taArr[i])
}
function Trim(text) {
	LTrim(text);
	RTrim(text)
}
function LTrim(text) {
	while (text.value.charAt(0) == " ")
		text.value = text.value.substring(1, text.value.length)
}
function RTrim(text) {
	while (text.value.charAt(text.value.length - 1) == " ")
		text.value = text.value.substring(0, text.value.length - 1)
};
// CommonScript.js
function createCookie(name, value, days) {
	if (days) {
		var date = new Date;
		date.setTime(date.getTime() + days * 24 * 60 * 60 * 1E3);
		var expires = "; expires=" + date.toGMTString()
	} else
		var expires = "";
	document.cookie = name + "=" + value + expires + "; path=/"
}
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(";");
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == " ")
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length)
	}
	return null
}
function eraseCookie(name) {
	createCookie(name, "", -1)
};
// disable.js
document.oncontextmenu = function() {
	window.status = "Right-click is disabled";
	alert("Right-click is disabled");
	return false
};

if (document.addEventListener)
	document.addEventListener("keydown", my_onkeydown_handler, true);
else if (document.attachEvent)
	document.attachEvent("onkeydown", my_onkeydown_handler);
else
	document.onkeydown = my_onkeydown_handler;
function my_onkeydown_handler() {
	var bool = true;
	var theEvent = window.event || arguments[0];
	switch (theEvent.keyCode || theEvent.which) {
	case 116:
		window.status = "F5 is disabled.";
		window.setTimeout("window.status='';", 2E3);
		alert("F5 is disabled.");
		bool = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
		if (document.all && window.event && !theEvent.preventDefault) {
			event.cancelBubble = true;
			theEvent.returnValue = false;
			theEvent.keyCode = 0
		}
		break
	}
	if (!bool)
		return false
}
function supressEnterKeySubmit() {
	var d = document.getElementsByTagName("INPUT");
	if (d != null)
		for (var f = 0; f < d.length; f++) {
			var e = d[f];
			if (e.type == "text" || e.type == "textarea")
				if (window.attachEvent)
					e.onkeydown = function(a) {
						var b;
						if (a && a.which) {
							a = a;
							b = a.which
						} else {
							a = event;
							b = a.keyCode
						}
						if (b == 13)
							return false
					};
				else if (window.addEventListener)
					e.onkeydown = function(a) {
						var b;
						if (a && a.which) {
							a = a;
							b = a.which
						} else {
							a = event;
							b = a.keyCode
						}
						if (b == 13)
							return false
					}
		}
};

function makeUppercase(a) {
	a.value = a.value.toUpperCase();
}
function limitChars(element, dispLabel, maxLength) {
	var str = element.value;
	if (str.length <= maxLength) {
		document.getElementById(dispLabel).innerHTML = maxLength - str.length;
	} else {
		document.getElementById(dispLabel).innerHTML = 0;
		element.value = str.substr(0, maxLength);
	}
}
function checkRadioVal(idObj) {
	for (i = 0; i < idObj.length; i++) {
		if (idObj[i].checked == true) {
			return idObj[i].value;
		}
	}
}
// Regarding dropdown manipulation
function emptyDropdownList(box) {
	while (box.options.length)
		box.remove(0);
	box.options.add(new Option("Select", "select"));
}
function fillDropdownList(box, arr) {
	emptyDropdownList(box);
	for (i = 0; i < arr.length; i++) {
		var a = arr[i].split(":");
		var opt = document.createElement("option");
		opt.text = a[0];
		opt.value = a[1];
		box.options.add(opt);
	}
	box.selectedIndex = 0;
}
function in_array(array, id) {
	for (var i = 0; i < array.length; i++) {
		if (array[i] == id) {
			return true;
		}
	}
	return false;
}

function isValidAddress(addressField) {
	var len, str, str1, i;
	str = addressField.value;
	len = addressField.value.length;
	str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()-.\/\\&\n ";
	for (i = 0; i < len; i++) {
		if ((str1.indexOf(str.charAt(i))) == -1) {
			alert("Enter AlphaNumeric Data in this field.\nAllowed characters are alphabets, numbers ( ) . & - / \\");
			addressField.value = "";
			addressField.focus();
			break;
		}
	}
	return true;
}
function validateVehicleNo(Obj) {
	Obj.value = Obj.value.toUpperCase();
	var val = Obj.value;
	var patt = "[A-Z]{2}[-][0-9]{2}[ ][A-Z]{2}[ ][0-9]{4}$";
	var reg = new RegExp(patt);
	if (!val.match(reg) && val.length != 0) {
		alert("Please enter Vehicle No. in format XX-99 XX 9999");
		Obj.value = "";
	}
}