/**
 * anthor:yate 
 * created:20140110
 * email:e.yangting@gmail.com 
 * desc:Simple table with CRUD and paging functions.
 * required:jquery + bootstrap
 */
(function($) {
    var privateFunction = function() {
    }
 
    var methods = {
        init: function(options) {
        	var defaults = {
        			param:{},
        			uri:"",
        			idx:1,
                    rows:10
            };
        	var settings = $.extend(defaults, options);
        	this.data = settings
        	return this;
        },
        destroy: function(options) {        	
            return this;
        },
        load:function(options){
        	$this=this;
        	if(!options)
        		options = $this.data;
        	$.ajax({
				type : "post",
				url : options.uri+"/"+options.rows+"/"+options.idx,
				data : options.param,
				dataType : 'json',
				success : function(resp) {
					$this.children("tbody").remove();
					$this.append("<tbody>");
					resp.data.forEach(function(e,i){
						$this.append("<tr><td><input type=\"checkbox\" name=\"ids\" value=\""+e.id+"\"></td><td>"+e.id+"</td><td>"+e.appName+"</td><td>"+e.trunk+"</td><td>"+e.state+"</td><td><a href=\"javascript:void(0);\" onclick=\"info("+e.id+")\" data-toggle=\"modal\" data-target=\"#modify\"><span class=\"glyphicon glyphicon-saved\"></span></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-th-list\"></a></span></td></tr>");
					});
					$this.append("</tbody>");
					
					$this.children("tfoot").remove();
					$this.append("<tfoot><ul class=\"pagination\">");
					for(i=Math.max(1,resp.idx-10);i<=Math.min(resp.idx+10,resp.count);i++){
						$this.append("<li><a href=\"javascript:void(0);\" onclick=\"javascript:go(10,"+i+");\">"+i+"</a></li>");
					}
					$this.append("</ul></tfoot>");
				}
			});
        },
        add:function(options){
        	return this;
        },
        modify:function(options){
        	return this;
        },
        removes:function(options){
        	return this;
        },
        query:function(options){
        	return this;
        }
    };
    
    var tableHtml = "<tr><td><input type=\"checkbox\" name=\"ids\" value=\"{id}\"></td><td>{id}</td><td>{appName}</td><td>{trunk}</td><td>{state}</td><td><a href=\"javascript:void(0);\" onclick=\"info({id})\" data-toggle=\"modal\" data-target=\"#modify\"><span class=\"glyphicon glyphicon-saved\"></span></a>&nbsp;&nbsp;<a href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-th-list\"></a></span></td></tr>";
 
    $.fn.endparam = function() {
        var method = arguments[0];
        if(methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }
        return method.apply(this, arguments);
    }
    
//    format : function(f,data) {
//    	var html = f;
//    	html.replace(/{id}/g,data.id);
//    	html.replace(/{appName}/g,data.appName);
//    	html.replace(/{trunk}/g,data.trunk);
//    	html.replace(/{state}/g,data.state);
//    	return html;
//    }
// 
})(jQuery);