(function(a) {
	var b = a(document).data("func", {});
	a.smartMenu = a.noop;
	a.fn.smartMenu = function(f, c) {
		var i = a("body"), g = {
			name : "",
			offsetX : 2,
			offsetY : 2,
			textLimit : 6,
			beforeShow : a.noop,
			afterShow : a.noop
		};
		var h = a.extend(g, c || {});
		var e = function(k) {
			var m = k || f, j = k ? Math.random().toString() : h.name, o = "", n = "", l = "smart_menu_";
			if (a.isArray(m) && m.length) {
				o = '<div id="smartMenu_' + j + '" class="' + l
						+ 'box"><div class="' + l + 'body"><ul class="' + l
						+ 'ul">';
				a
						.each(
								m,
								function(q, p) {
									if (q) {
										o = o + '<li class="' + l
												+ 'li_separate">&nbsp;</li>'
									}
									if (a.isArray(p)) {
										a
												.each(
														p,
														function(s, v) {
															var w = v.text, u = "", r = "", t = Math
																	.random()
																	.toString()
																	.replace(
																			".",
																			"");
															if (w) {
																if (w.length > h.textLimit) {
																	w = w
																			.slice(
																					0,
																					h.textLimit)
																			+ "…";
																	r = ' title="'
																			+ v.text
																			+ '"'
																}
																if (a
																		.isArray(v.data)
																		&& v.data.length) {
																	u = '<li class="'
																			+ l
																			+ 'li" data-hover="true">'
																			+ e(v.data)
																			+ '<a href="javascript:" class="'
																			+ l
																			+ 'a"'
																			+ r
																			+ ' data-key="'
																			+ t
																			+ '"><i class="'
																			+ l
																			+ 'triangle"></i>'
																			+ w
																			+ "</a></li>"
																} else {
																	u = '<li class="'
																			+ l
																			+ 'li"><a href="javascript:" class="'
																			+ l
																			+ 'a"'
																			+ r
																			+ ' data-key="'
																			+ t
																			+ '">'
																			+ w
																			+ "</a></li>"
																}
																o += u;
																var x = b
																		.data("func");
																x[t] = v.func;
																b.data("func",
																		x)
															}
														})
									}
								});
				o = o + "</ul></div></div>"
			}
			return o
		}, d = function() {
			var j = "#smartMenu_", l = "smart_menu_", k = a(j + h.name);
			if (!k.size()) {
				a("body").append(e());
				a(j + h.name + " a").bind("click", function() {
					var m = a(this).attr("data-key"), n = b.data("func")[m];
					if (a.isFunction(n)) {
						n.call(b.data("trigger"))
					}
					a.smartMenu.hide();
					return false
				});
				a(j + h.name + " li").each(
						function() {
							var m = a(this).attr("data-hover"), n = l
									+ "li_hover";
							a(this).hover(
									function() {
										var o = a(this).siblings("." + n);
										o.removeClass(n).children(
												"." + l + "box").hide();
										o.children("." + l + "a").removeClass(
												l + "a_hover");
										if (m) {
											a(this).addClass(n).children(
													"." + l + "box").show();
											a(this).children("." + l + "a")
													.addClass(l + "a_hover")
										}
									})
						});
				return a(j + h.name)
			}
			return k
		};
		a(this).each(function() {
			this.oncontextmenu = function(l) {
				if (a.isFunction(h.beforeShow)) {
					h.beforeShow.call(this)
				}
				l = l || window.event;
				l.cancelBubble = true;
				if (l.stopPropagation) {
					l.stopPropagation()
				}
				a.smartMenu.hide();
				var k = b.scrollTop();
				var j = d();
				if (j) {
					j.css({
						display : "block",
						left : l.clientX + h.offsetX,
						top : l.clientY + k + h.offsetY
					});
					b.data("target", j);
					b.data("trigger", this);
					if (a.isFunction(h.afterShow)) {
						h.afterShow.call(this)
					}
					return false
				}
			}
		});
		if (!i.data("bind")) {
			i.bind("click", a.smartMenu.hide).data("bind", true)
		}
	};
	a.extend(a.smartMenu, {
		hide : function() {
			var c = b.data("target");
			if (c && c.css("display") === "block") {
				c.hide()
			}
		},
		remove : function() {
			var c = b.data("target");
			if (c) {
				c.remove()
			}
		}
	})
})(jQuery);