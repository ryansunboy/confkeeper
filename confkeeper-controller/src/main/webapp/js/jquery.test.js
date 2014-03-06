/**
 * anthor:yate created:20140110 email:e.yangting@gmail.com desc:Simple table
 * with CRUD and paging functions. required:jquery + bootstrap
 */
(function($) {

	var _default = {
		query : {
			type: "post",
			uri : "",
			inx : 1,
			rows : 10,
			param : {i}
		}
	};

	$.fn.mytable = function(options) {
		_default = $.extend(_default, options);

		return this.each(function(i, d) {

		});
	}

	function add(callback, args) {
		return callback(args);
	}

	function modify(callback, args) {
		return callback(args);
	}

	function remove(callback, args) {
		return callback(args);
	}

	function submit(callback, args) {
		$this = this;
		if (!options)
			options = $this.data;
		$
				.ajax({
					type : "post",
					url : options.uri + "/" + options.rows + "/" + options.idx,
					data : options.param,
					dataType : 'json',
					success : function(resp) {
						$this.children("tbody").remove();
						$this.children("ul").remove();
						$this.append("<tbody>");
						resp.data
								.forEach(function(e, i) {
									$this
											.append("<tr><td><input type=\"checkbox\" name=\"ids\" value=\""
													+ e.id
													+ "\"></td><td>"
													+ e.id
													+ "</td><td>"
													+ e.appName
													+ "</td><td>"
													+ e.appNote
													+ "</td><td><a href=\"javascript:void(0);\" onclick=\"info("
													+ e.id
													+ ")\" data-toggle=\"modal\" data-target=\"#modify\"><span class=\"glyphicon glyphicon-saved\"></span></a></td></tr>");
								});
						$this.append("</tbody>");

						$this.append("<ul class=\"pagination\">");
						for (i = Math.max(1, resp.idx - 10); i <= Math.min(
								resp.idx + 10, resp.count); i++) {
							$this
									.append("<li><a href=\"javascript:void(0);\" onclick=\"javascript:go(10,"
											+ i + ");\">" + i + "</a></li>");
						}
						$this.append("</ul>");
					}
				});
	}
})(jQuery);