 //性别
      new SelectorJS.selector.init({
        id:'#sex1',
        data: [["0", "不限"], ["10", "男"], ["11", "女"] ],
        value:'0'
      });  
 
      //婚否
      new SelectorJS.selector.init({
        id:'#Marriage1',
        data: [["0", "不限"], ["10", "未婚"], ["11", "已婚"], ["12", "单身"] ],
        value:'0'
      });
      
       //地区
      new SelectorJS.selector.init({
        id:'#area1',
        data: [["", "请选择"],["北京", "北京"], ["天津", "天津"], ["河北", "河北"], ["山西", "山西"], ["内蒙古", "内蒙古"], ["辽宁", "辽宁"], ["吉林", "吉林"], ["黑龙江", "黑龙江"], ["上海", "上海"], ["江苏", "江苏"], ["浙江", "浙江"], ["安徽", "安徽"], ["福建", "福建"], ["江西", "江西"], ["山东", "山东"], ["河南", "河南"], ["湖北", "湖北"], ["湖南", "湖南"], ["广东", "广东"], ["广西", "广西"], ["海南", "海南"], ["重庆", "重庆"], ["四川", "四川"], ["贵州", "贵州"], ["云南", "云南"], ["西藏", "西藏"], ["陕西", "陕西"], ["甘肃", "甘肃"], ["青海", "青海"], ["宁夏", "宁夏"], ["新疆", "新疆"] ],
        value:''
      });
      
       //照片
      new SelectorJS.selector.init({
        id:'#photo1',
        data: [["0", "不限"], ["1", "有"], ["2", "无"] ],
        value:'0'
      });
      
      
      $(function(){

    	    var ZCDetail=(function(){
    	        return {
    	            init:function(){
    	                this.bindEvent(); 
    	            }, 
    	            bindEvent:function(){
    	                var root=this;
    	               
 
    	                $("#ptss").click(function(){ 
    	                	youkeSearch.submit(); 
    	                });
    	                
    					
    	            }
    	        }
    	    })();
    	    ZCDetail.init();
    	});