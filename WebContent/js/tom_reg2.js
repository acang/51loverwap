function update_province( CatID1, subTypeForm ){
	subTypeForm.length=0;
	switch(CatID1){
	case "-1":
		subTypeForm.options[0]=new Option("--------","-1");
		subTypeForm.options[0].selected=true;break;
	case "中国"://中国
		subTypeForm.options[0]=new Option("北京","北京");
		subTypeForm.options[1]=new Option("天津","天津");
		subTypeForm.options[2]=new Option("河北","河北");
		subTypeForm.options[3]=new Option("山西","山西");
		subTypeForm.options[4]=new Option("内蒙古","内蒙古");
		subTypeForm.options[5]=new Option("辽宁","辽宁");
		subTypeForm.options[6]=new Option("吉林","吉林");
		subTypeForm.options[7]=new Option("黑龙江","黑龙江");
		subTypeForm.options[8]=new Option("上海","上海");
		subTypeForm.options[9]=new Option("江苏","江苏");
		subTypeForm.options[10]=new Option("浙江","浙江");
		subTypeForm.options[11]=new Option("安徽","安徽");
		subTypeForm.options[12]=new Option("福建","福建");
		subTypeForm.options[13]=new Option("江西","江西");
		subTypeForm.options[14]=new Option("山东","山东");
		subTypeForm.options[15]=new Option("河南","河南");
		subTypeForm.options[16]=new Option("湖北","湖北");
		subTypeForm.options[17]=new Option("湖南","湖南");
		subTypeForm.options[18]=new Option("广东","广东");
		subTypeForm.options[19]=new Option("广西","广西");
		subTypeForm.options[20]=new Option("海南","海南");
		subTypeForm.options[21]=new Option("重庆","重庆");
		subTypeForm.options[22]=new Option("四川","四川");
		subTypeForm.options[23]=new Option("贵州","贵州");
		subTypeForm.options[24]=new Option("云南","云南");
		subTypeForm.options[25]=new Option("西藏","西藏");
		subTypeForm.options[26]=new Option("陕西","陕西");
		subTypeForm.options[27]=new Option("甘肃","甘肃");
		subTypeForm.options[28]=new Option("青海","青海");
		subTypeForm.options[29]=new Option("宁夏","宁夏");
		subTypeForm.options[30]=new Option("新疆","新疆");
		subTypeForm.options[31]=new Option("香港","香港");
		subTypeForm.options[32]=new Option("澳门","澳门");
		subTypeForm.options[33]=new Option("台湾","台湾");
		subTypeForm.options[0].selected=true;
	break;
	}
return -1;
}
function update_city( CatID1, CatID2, subTypeForm ){
subTypeForm.length=0;
switch(CatID1){
case "-1":
	subTypeForm.options[0]=new Option("--------","-1");
	subTypeForm.options[0].selected=true;break;
case "中国"://中国
	switch ( CatID2 ){
	case "广东"://广东

		subTypeForm.options[0]=new Option("广州","广州");
		subTypeForm.options[1]=new Option("深圳","深圳");
		subTypeForm.options[2]=new Option("珠海","珠海");
		subTypeForm.options[3]=new Option("汕头","汕头");
		subTypeForm.options[4]=new Option("韶关","韶关");
		subTypeForm.options[5]=new Option("河源","河源");
		subTypeForm.options[6]=new Option("梅州","梅州");
		subTypeForm.options[7]=new Option("惠州","惠州");
		subTypeForm.options[8]=new Option("汕尾","汕尾");
		subTypeForm.options[9]=new Option("东莞","东莞");
		subTypeForm.options[10]=new Option("中山","中山");
		subTypeForm.options[11]=new Option("江门","江门");
		subTypeForm.options[12]=new Option("佛山","佛山");
		subTypeForm.options[13]=new Option("阳江","阳江");
		subTypeForm.options[14]=new Option("湛江","湛江");
		subTypeForm.options[15]=new Option("茂名","茂名");
		subTypeForm.options[16]=new Option("肇庆","肇庆");
		subTypeForm.options[17]=new Option("清远","清远");
		subTypeForm.options[18]=new Option("潮州","潮州");
		subTypeForm.options[19]=new Option("揭阳","揭阳");
		subTypeForm.options[20]=new Option("云浮","云浮");
		subTypeForm.options[0].selected=true;
		break;
	case "广西"://广西

		subTypeForm.options[0]=new Option("南宁","南宁");
		subTypeForm.options[1]=new Option("柳州","柳州");
		subTypeForm.options[2]=new Option("桂林","桂林");
		subTypeForm.options[3]=new Option("梧州","梧州");
		subTypeForm.options[4]=new Option("北海","北海");
		subTypeForm.options[5]=new Option("防城港","防城港");
		subTypeForm.options[6]=new Option("钦州","钦州");
		subTypeForm.options[7]=new Option("贵港","贵港");
		subTypeForm.options[8]=new Option("玉林","玉林");
		subTypeForm.options[9]=new Option("百色","百色");
		subTypeForm.options[10]=new Option("贺州","贺州");
		subTypeForm.options[11]=new Option("河池","河池");
		subTypeForm.options[12]=new Option("来宾","来宾");
		subTypeForm.options[13]=new Option("崇左","崇左");
		subTypeForm.options[0].selected=true;
		break;
	case "北京"://北京
		subTypeForm.options[0]=new Option("东城区","东城区");
		subTypeForm.options[1]=new Option("西城区","西城区");
		subTypeForm.options[2]=new Option("崇文区","崇文区");
		subTypeForm.options[3]=new Option("宣武区","宣武区");
		subTypeForm.options[4]=new Option("朝阳区","朝阳区");
		subTypeForm.options[5]=new Option("丰台区","丰台区");
		subTypeForm.options[6]=new Option("石景山区","石景山区");
		subTypeForm.options[7]=new Option("海淀区","海淀区");
		subTypeForm.options[8]=new Option("门头沟区","门头沟区");
		subTypeForm.options[9]=new Option("房山区","房山区");
		subTypeForm.options[10]=new Option("通州区","通州区");
		subTypeForm.options[11]=new Option("顺义区","顺义区");
		subTypeForm.options[12]=new Option("昌平区","昌平区");
		subTypeForm.options[13]=new Option("大兴区","大兴区");
		subTypeForm.options[14]=new Option("怀柔区","怀柔区");
		subTypeForm.options[15]=new Option("平谷区","平谷区");
		subTypeForm.options[16]=new Option("密云县","密云县");
		subTypeForm.options[17]=new Option("延庆区","延庆区");
		subTypeForm.options[0].selected=true;
		break;
	case "海南"://海南

		subTypeForm.options[0]=new Option("海口","海口");
		subTypeForm.options[1]=new Option("三亚","三亚");
		subTypeForm.options[2]=new Option("其它","其它");
		subTypeForm.options[0].selected=true;
		break;
	case "福建"://福建

		subTypeForm.options[0]=new Option("福州","福州");
		subTypeForm.options[1]=new Option("厦门","厦门");
		subTypeForm.options[2]=new Option("三明","三明");
		subTypeForm.options[3]=new Option("莆田","莆田");
		subTypeForm.options[4]=new Option("泉州","泉州");
		subTypeForm.options[5]=new Option("漳州","漳州");
		subTypeForm.options[6]=new Option("南平","南平");
		subTypeForm.options[7]=new Option("龙岩","龙岩");
		subTypeForm.options[8]=new Option("宁德","宁德");
		subTypeForm.options[0].selected=true;
		break;
	case "天津"://天津
		subTypeForm.options[0]=new Option("和平区","和平区");
		subTypeForm.options[1]=new Option("河东区","河东区");
		subTypeForm.options[2]=new Option("河西区","河西区");
		subTypeForm.options[3]=new Option("南开区","南开区");
		subTypeForm.options[4]=new Option("河北区","河北区");
		subTypeForm.options[5]=new Option("红桥区","红桥区");
		subTypeForm.options[6]=new Option("塘沽区","塘沽区");
		subTypeForm.options[7]=new Option("汉沽区","汉沽区");
		subTypeForm.options[8]=new Option("大港区","大港区");
		subTypeForm.options[9]=new Option("东丽区","东丽区");
		subTypeForm.options[10]=new Option("西青区","西青区");
		subTypeForm.options[11]=new Option("津南区","津南区");
		subTypeForm.options[12]=new Option("北辰区","北辰区");
		subTypeForm.options[13]=new Option("武清区","武清区");
		subTypeForm.options[14]=new Option("宝坻区","宝坻区");
		subTypeForm.options[15]=new Option("宁河区","宁河区");
		subTypeForm.options[16]=new Option("静海县","静海县");
		subTypeForm.options[17]=new Option("蓟县","蓟县");
		subTypeForm.options[0].selected=true;
		break;
	case "湖南"://湖南

		subTypeForm.options[0]=new Option("长沙","长沙");
		subTypeForm.options[1]=new Option("株洲","株洲");
		subTypeForm.options[2]=new Option("湘潭","湘潭");
		subTypeForm.options[3]=new Option("衡阳","衡阳");
		subTypeForm.options[4]=new Option("邵阳","邵阳");
		subTypeForm.options[5]=new Option("岳阳","岳阳");
		subTypeForm.options[6]=new Option("常德","常德");
		subTypeForm.options[7]=new Option("张家界","张家界");
		subTypeForm.options[8]=new Option("益阳","益阳");
		subTypeForm.options[9]=new Option("郴州","郴州");
		subTypeForm.options[10]=new Option("永州","永州");
		subTypeForm.options[11]=new Option("怀化","怀化");
		subTypeForm.options[12]=new Option("娄底","娄底");
		subTypeForm.options[13]=new Option("湘西","湘西");
		subTypeForm.options[0].selected=true;
		break;
	case "湖北"://湖北

		subTypeForm.options[0]=new Option("武汉","武汉");
		subTypeForm.options[1]=new Option("黄石","黄石");
		subTypeForm.options[2]=new Option("襄樊","襄樊");
		subTypeForm.options[3]=new Option("十堰","十堰");
		subTypeForm.options[4]=new Option("荆州","荆州");
		subTypeForm.options[5]=new Option("宜昌","宜昌");
		subTypeForm.options[6]=new Option("荆门","荆门");
		subTypeForm.options[7]=new Option("鄂州","鄂州");
		subTypeForm.options[8]=new Option("孝感","孝感");
		subTypeForm.options[9]=new Option("黄冈","黄冈");
		subTypeForm.options[10]=new Option("咸宁","咸宁");
		subTypeForm.options[11]=new Option("随州","随州");
		subTypeForm.options[12]=new Option("恩施","恩施");
		//subTypeForm.options[13]=new Option("其它","其它");
		subTypeForm.options[0].selected=true;
		break;
	case "河南"://河南

		subTypeForm.options[0]=new Option("郑州","郑州");
		subTypeForm.options[1]=new Option("开封","开封");
		subTypeForm.options[2]=new Option("洛阳","洛阳");
		subTypeForm.options[3]=new Option("平顶山","平顶山");
		subTypeForm.options[4]=new Option("焦作","焦作");
		subTypeForm.options[5]=new Option("鹤壁","鹤壁");
		subTypeForm.options[6]=new Option("新乡","新乡");
		subTypeForm.options[7]=new Option("安阳","安阳");
		subTypeForm.options[8]=new Option("濮阳","濮阳");
		subTypeForm.options[9]=new Option("许昌","许昌");
		subTypeForm.options[10]=new Option("漯河","漯河");
		subTypeForm.options[11]=new Option("三门峡","三门峡");
		subTypeForm.options[12]=new Option("南阳","南阳");
		subTypeForm.options[13]=new Option("商丘","商丘");
		subTypeForm.options[14]=new Option("信阳","信阳");
		subTypeForm.options[15]=new Option("周口","周口");
		subTypeForm.options[16]=new Option("驻马店","驻马店");
                subTypeForm.options[17]=new Option("济源","济源");
		subTypeForm.options[18]=new Option("其它","其它");
		subTypeForm.options[0].selected=true;
		break;
	case "河北"://河北

		subTypeForm.options[0]=new Option("石家庄","石家庄");
		subTypeForm.options[1]=new Option("唐山","唐山");
		subTypeForm.options[2]=new Option("秦皇岛","秦皇岛");
		subTypeForm.options[3]=new Option("邯郸","邯郸");
		subTypeForm.options[4]=new Option("邢台","邢台");
		subTypeForm.options[5]=new Option("保定","保定");
		subTypeForm.options[6]=new Option("张家口","张家口");
		subTypeForm.options[7]=new Option("承德","承德");
		subTypeForm.options[8]=new Option("沧州","沧州");
		subTypeForm.options[9]=new Option("廊坊","廊坊");
		subTypeForm.options[10]=new Option("衡水","衡水");
		subTypeForm.options[0].selected=true;
		break;
	case "山东"://山东

		subTypeForm.options[0]=new Option("济南","济南");
		subTypeForm.options[1]=new Option("青岛","青岛");
		subTypeForm.options[2]=new Option("淄博","淄博");
		subTypeForm.options[3]=new Option("枣庄","枣庄");
		subTypeForm.options[4]=new Option("东营","东营");
		subTypeForm.options[5]=new Option("潍坊","潍坊");
		subTypeForm.options[6]=new Option("烟台","烟台");
		subTypeForm.options[7]=new Option("威海","威海");
		subTypeForm.options[8]=new Option("济宁","济宁");
		subTypeForm.options[9]=new Option("泰安","泰安");
		subTypeForm.options[10]=new Option("日照","日照");
		subTypeForm.options[11]=new Option("莱芜","莱芜");
		subTypeForm.options[12]=new Option("临沂","临沂");
		subTypeForm.options[13]=new Option("德州","德州");
		subTypeForm.options[14]=new Option("聊城","聊城");
		subTypeForm.options[15]=new Option("滨州","滨州");
		subTypeForm.options[16]=new Option("菏泽","菏泽");
		subTypeForm.options[0].selected=true;
		break;
	case "山西"://山西

		subTypeForm.options[0]=new Option("太原","太原");
		subTypeForm.options[1]=new Option("大同","大同");
		subTypeForm.options[2]=new Option("阳泉","阳泉");
		subTypeForm.options[3]=new Option("长治","长治");
		subTypeForm.options[4]=new Option("晋城","晋城");
		subTypeForm.options[5]=new Option("朔州","朔州");
		subTypeForm.options[6]=new Option("晋中","晋中");
		subTypeForm.options[7]=new Option("运城","运城");
		subTypeForm.options[8]=new Option("忻州","忻州");
		subTypeForm.options[9]=new Option("临汾","临汾");
		subTypeForm.options[10]=new Option("吕梁","吕梁");
		subTypeForm.options[0].selected=true;
		break;
	case "黑龙江"://黑龙江

		subTypeForm.options[0]=new Option("哈尔滨","哈尔滨");
		subTypeForm.options[1]=new Option("齐齐哈尔","齐齐哈尔");
		subTypeForm.options[2]=new Option("鹤岗","鹤岗");
		subTypeForm.options[3]=new Option("双鸭山","双鸭山");
		subTypeForm.options[4]=new Option("鸡西","鸡西");
		subTypeForm.options[5]=new Option("大庆","大庆");
		subTypeForm.options[6]=new Option("伊春","伊春");
		subTypeForm.options[7]=new Option("牡丹江","牡丹江");
		subTypeForm.options[8]=new Option("佳木斯","佳木斯");
		subTypeForm.options[9]=new Option("七台河","七台河");
		subTypeForm.options[10]=new Option("黑河","黑河");
		subTypeForm.options[11]=new Option("绥化","绥化");
		subTypeForm.options[12]=new Option("大兴安岭","大兴安岭");
		subTypeForm.options[0].selected=true;
		break;
	case "辽宁"://辽宁

		subTypeForm.options[0]=new Option("沈阳","沈阳");
		subTypeForm.options[1]=new Option("大连","大连");
		subTypeForm.options[2]=new Option("鞍山","鞍山");
		subTypeForm.options[3]=new Option("抚顺","抚顺");
		subTypeForm.options[4]=new Option("本溪","本溪");
		subTypeForm.options[5]=new Option("丹东","丹东");
		subTypeForm.options[6]=new Option("锦州","锦州");
		subTypeForm.options[7]=new Option("葫芦岛","葫芦岛");
		subTypeForm.options[8]=new Option("营口","营口");
		subTypeForm.options[9]=new Option("盘锦","盘锦");
		subTypeForm.options[10]=new Option("阜新","阜新");
		subTypeForm.options[11]=new Option("辽阳","辽阳");
		subTypeForm.options[12]=new Option("铁岭","铁岭");
		subTypeForm.options[13]=new Option("朝阳","朝阳");
		subTypeForm.options[0].selected=true;
		break;
	case "上海"://上海
		subTypeForm.options[0]=new Option("黄浦区","黄浦区");
		subTypeForm.options[1]=new Option("卢湾区","卢湾区");
		subTypeForm.options[2]=new Option("徐汇区","徐汇区");
		subTypeForm.options[3]=new Option("长宁区","长宁区");
		subTypeForm.options[4]=new Option("静安区","静安区");
		subTypeForm.options[5]=new Option("普陀区","普陀区");
		subTypeForm.options[6]=new Option("闸北区","闸北区");
		subTypeForm.options[7]=new Option("虹口区","虹口区");
		subTypeForm.options[8]=new Option("杨浦区","杨浦区");
		subTypeForm.options[9]=new Option("宝山区","宝山区");
		subTypeForm.options[10]=new Option("闵行区","闵行区");
		subTypeForm.options[11]=new Option("嘉定区","嘉定区");
		subTypeForm.options[12]=new Option("浦东新区","浦东新区");
		subTypeForm.options[13]=new Option("金山区","金山区");
		subTypeForm.options[14]=new Option("松江区","松江区");
		subTypeForm.options[15]=new Option("青浦区","青浦区");
		subTypeForm.options[16]=new Option("南汇区","南汇区");
		subTypeForm.options[17]=new Option("奉贤区","奉贤区");
		subTypeForm.options[18]=new Option("崇明县","崇明县");
		subTypeForm.options[0].selected=true;
		break;
	case "甘肃"://甘肃

		subTypeForm.options[0]=new Option("兰州","兰州");
		subTypeForm.options[1]=new Option("金昌","金昌");
		subTypeForm.options[2]=new Option("白银","白银");
		subTypeForm.options[3]=new Option("天水","天水");
		subTypeForm.options[4]=new Option("嘉峪关","嘉峪关");
		subTypeForm.options[5]=new Option("武威","武威");
		subTypeForm.options[6]=new Option("张掖","张掖");
		subTypeForm.options[7]=new Option("平凉","平凉");
		subTypeForm.options[8]=new Option("酒泉","酒泉");
		subTypeForm.options[9]=new Option("庆阳","庆阳");
		subTypeForm.options[10]=new Option("定西","定西");
		subTypeForm.options[11]=new Option("陇南","陇南");
		subTypeForm.options[12]=new Option("甘南","甘南");
		subTypeForm.options[13]=new Option("临夏","临夏");
		subTypeForm.options[0].selected=true;
		break;
	case "青海"://青海

		subTypeForm.options[0]=new Option("西宁","西宁");
		subTypeForm.options[1]=new Option("海东","海东");
		subTypeForm.options[2]=new Option("海北","海北");
		subTypeForm.options[3]=new Option("黄南","黄南");
		subTypeForm.options[4]=new Option("海南","海南");
		subTypeForm.options[5]=new Option("果洛","果洛");
		subTypeForm.options[6]=new Option("玉树","玉树");
		subTypeForm.options[7]=new Option("海西","海西");
		subTypeForm.options[0].selected=true;
		break;
	case "新疆"://新疆

		subTypeForm.options[0]=new Option("乌鲁木齐","乌鲁木齐");
		subTypeForm.options[1]=new Option("克拉玛依","克拉玛依");
		subTypeForm.options[2]=new Option("石河子","石河子");
		subTypeForm.options[3]=new Option("吐鲁番","吐鲁番");
		subTypeForm.options[4]=new Option("哈密","哈密");
		subTypeForm.options[5]=new Option("和田","和田");
		subTypeForm.options[6]=new Option("阿克苏","阿克苏");
		subTypeForm.options[7]=new Option("喀什","喀什");
		subTypeForm.options[8]=new Option("克孜勒苏","克孜勒苏");
		subTypeForm.options[9]=new Option("巴音郭楞","巴音郭楞");
		subTypeForm.options[10]=new Option("昌吉","昌吉");
		subTypeForm.options[11]=new Option("博尔塔拉","博尔塔拉");
		subTypeForm.options[12]=new Option("伊犁","伊犁");
		subTypeForm.options[13]=new Option("塔城","塔城");
		subTypeForm.options[14]=new Option("阿勒泰","阿勒泰");
		subTypeForm.options[0].selected=true;
		break;
	case "西藏"://西藏

		subTypeForm.options[0]=new Option("拉萨","拉萨");
		subTypeForm.options[1]=new Option("那曲","那曲");
		subTypeForm.options[2]=new Option("昌都","昌都");
		subTypeForm.options[3]=new Option("山南","山南");
		subTypeForm.options[4]=new Option("日喀则","日喀则");
		subTypeForm.options[5]=new Option("阿里","阿里");
		subTypeForm.options[6]=new Option("林芝","林芝");
		subTypeForm.options[0].selected=true;
		break;
	case "宁夏"://宁夏

		subTypeForm.options[0]=new Option("银川","银川");
		subTypeForm.options[1]=new Option("石嘴山","石嘴山");
		subTypeForm.options[2]=new Option("吴忠","吴忠");
		subTypeForm.options[3]=new Option("固原","固原");
		subTypeForm.options[0].selected=true;
		break;
	case "四川"://四川

		subTypeForm.options[0]=new Option("成都","成都");
		subTypeForm.options[1]=new Option("自贡","自贡");
		subTypeForm.options[2]=new Option("攀枝花","攀枝花");
		subTypeForm.options[3]=new Option("泸州","泸州");
		subTypeForm.options[4]=new Option("德阳","德阳");
		subTypeForm.options[5]=new Option("绵阳","绵阳");
		subTypeForm.options[6]=new Option("广元","广元");
		subTypeForm.options[7]=new Option("遂宁","遂宁");
		subTypeForm.options[8]=new Option("内江","内江");
		subTypeForm.options[9]=new Option("乐山","乐山");
		subTypeForm.options[10]=new Option("南充","南充");
		subTypeForm.options[11]=new Option("宜宾","宜宾");
		subTypeForm.options[12]=new Option("广安","广安");
		subTypeForm.options[13]=new Option("达州","达州");
		subTypeForm.options[14]=new Option("眉山","眉山");
		subTypeForm.options[15]=new Option("雅安","雅安");
		subTypeForm.options[16]=new Option("巴中","巴中");
		subTypeForm.options[17]=new Option("资阳","资阳");
		subTypeForm.options[18]=new Option("阿坝","阿坝");
		subTypeForm.options[19]=new Option("甘孜","甘孜");
		subTypeForm.options[20]=new Option("凉山","凉山");
		subTypeForm.options[0].selected=true;
		break;
	case "云南"://云南

		subTypeForm.options[0]=new Option("昆明","昆明");
		subTypeForm.options[1]=new Option("曲靖","曲靖");
		subTypeForm.options[2]=new Option("玉溪","玉溪");
		subTypeForm.options[3]=new Option("保山","保山");
		subTypeForm.options[4]=new Option("昭通","昭通");
		subTypeForm.options[5]=new Option("思茅","思茅");
		subTypeForm.options[6]=new Option("临沧","临沧");
		subTypeForm.options[7]=new Option("丽江","丽江");
		subTypeForm.options[8]=new Option("文山","文山");
		subTypeForm.options[9]=new Option("红河","红河");
		subTypeForm.options[10]=new Option("西双版纳","西双版纳");
		subTypeForm.options[11]=new Option("楚雄","楚雄");
		subTypeForm.options[12]=new Option("大理","大理");
		subTypeForm.options[13]=new Option("德宏","德宏");
		subTypeForm.options[14]=new Option("怒江","怒江");
		subTypeForm.options[15]=new Option("迪庆","迪庆");
		subTypeForm.options[0].selected=true;
		break;
	case "吉林"://吉林

		subTypeForm.options[0]=new Option("长春","长春");
		subTypeForm.options[1]=new Option("吉林","吉林");
		subTypeForm.options[2]=new Option("四平","四平");
		subTypeForm.options[3]=new Option("辽源","辽源");
		subTypeForm.options[4]=new Option("通化","通化");
		subTypeForm.options[5]=new Option("白山","白山");
		subTypeForm.options[6]=new Option("松原","松原");
		subTypeForm.options[7]=new Option("白城","白城");
		subTypeForm.options[8]=new Option("延边","延边");
		subTypeForm.options[0].selected=true;
		break;
	case "内蒙古"://内蒙古

		subTypeForm.options[0]=new Option("呼和浩特","呼和浩特");
		subTypeForm.options[1]=new Option("包头","包头");
		subTypeForm.options[2]=new Option("乌海","乌海");
		subTypeForm.options[3]=new Option("赤峰","赤峰");
		subTypeForm.options[4]=new Option("通辽","通辽");
		subTypeForm.options[5]=new Option("鄂尔多斯","鄂尔多斯");
		subTypeForm.options[6]=new Option("呼伦贝尔","呼伦贝尔");
		subTypeForm.options[7]=new Option("乌兰察布","乌兰察布");
		subTypeForm.options[8]=new Option("锡林郭勒","锡林郭勒");
		subTypeForm.options[9]=new Option("巴彦淖尔","巴彦淖尔");
		subTypeForm.options[10]=new Option("阿拉善","阿拉善");
		subTypeForm.options[11]=new Option("兴安","兴安");
		subTypeForm.options[0].selected=true;
		break;
	case "陕西"://陕西

		subTypeForm.options[0]=new Option("西安","西安");
		subTypeForm.options[1]=new Option("铜川","铜川");
		subTypeForm.options[2]=new Option("宝鸡","宝鸡");
		subTypeForm.options[3]=new Option("咸阳","咸阳");
		subTypeForm.options[4]=new Option("渭南","渭南");
		subTypeForm.options[5]=new Option("延安","延安");
		subTypeForm.options[6]=new Option("汉中","汉中");
		subTypeForm.options[7]=new Option("榆林","榆林");
		subTypeForm.options[8]=new Option("安康","安康");
		subTypeForm.options[9]=new Option("商洛","商洛");
		subTypeForm.options[0].selected=true;
		break;
	case "安徽"://安徽

		subTypeForm.options[0]=new Option("合肥","合肥");
		subTypeForm.options[1]=new Option("芜湖","芜湖");
		subTypeForm.options[2]=new Option("蚌埠","蚌埠");
		subTypeForm.options[3]=new Option("淮南","淮南");
		subTypeForm.options[4]=new Option("马鞍山","马鞍山");
		subTypeForm.options[5]=new Option("淮北","淮北");
		subTypeForm.options[6]=new Option("铜陵","铜陵");
		subTypeForm.options[7]=new Option("安庆","安庆");
		subTypeForm.options[8]=new Option("黄山","黄山");
		subTypeForm.options[9]=new Option("滁州","滁州");
		subTypeForm.options[10]=new Option("阜阳","阜阳");
		subTypeForm.options[11]=new Option("宿州","宿州");
		subTypeForm.options[12]=new Option("巢湖","巢湖");
		subTypeForm.options[13]=new Option("六安","六安");
		subTypeForm.options[14]=new Option("毫州","毫州");
		subTypeForm.options[15]=new Option("池州","池州");
		subTypeForm.options[16]=new Option("宣城","宣城");
		subTypeForm.options[0].selected=true;
		break;
	case "贵州"://贵州

		subTypeForm.options[0]=new Option("贵阳","贵阳");
		subTypeForm.options[1]=new Option("六盘水","六盘水");
		subTypeForm.options[2]=new Option("遵义","遵义");
		subTypeForm.options[3]=new Option("安顺","安顺");
		subTypeForm.options[4]=new Option("铜仁","铜仁");
		subTypeForm.options[5]=new Option("毕节","毕节");
		subTypeForm.options[6]=new Option("黔西南","黔西南");
		subTypeForm.options[7]=new Option("黔东南","黔东南");
		subTypeForm.options[8]=new Option("黔南","黔南");
		subTypeForm.options[0].selected=true;
		break;
	case "江苏"://江苏

		subTypeForm.options[0]=new Option("南京","南京");
		subTypeForm.options[1]=new Option("徐州","徐州");
		subTypeForm.options[2]=new Option("连云港","连云港");
		subTypeForm.options[3]=new Option("淮安","淮安");
		subTypeForm.options[4]=new Option("宿迁","宿迁");
		subTypeForm.options[5]=new Option("盐城","盐城");
		subTypeForm.options[6]=new Option("扬州","扬州");
		subTypeForm.options[7]=new Option("泰州","泰州");
		subTypeForm.options[8]=new Option("南通","南通");
		subTypeForm.options[9]=new Option("镇江","镇江");
		subTypeForm.options[10]=new Option("常州","常州");
		subTypeForm.options[11]=new Option("无锡","无锡");
		subTypeForm.options[12]=new Option("苏州","苏州");
		subTypeForm.options[0].selected=true;
		break;
	case "重庆"://重庆
		subTypeForm.options[0]=new Option("渝中区","渝中区");
		subTypeForm.options[1]=new Option("大渡口","大渡口");
		subTypeForm.options[2]=new Option("江北区","江北区");
		subTypeForm.options[3]=new Option("沙坪坝","沙坪坝");
		subTypeForm.options[4]=new Option("九龙坡","九龙坡");
		subTypeForm.options[5]=new Option("南岸区","南岸区");
		subTypeForm.options[6]=new Option("北碚区","北碚区");
		subTypeForm.options[7]=new Option("万盛区","万盛区");
		subTypeForm.options[8]=new Option("双桥区","双桥区");
		subTypeForm.options[9]=new Option("渝北区","渝北区");
		subTypeForm.options[10]=new Option("巴南区","巴南区");
		subTypeForm.options[11]=new Option("万州区","万州区");
		subTypeForm.options[12]=new Option("涪陵区","涪陵区");
		subTypeForm.options[13]=new Option("黔江区","黔江区");
		subTypeForm.options[14]=new Option("长寿区","长寿区");
		subTypeForm.options[15]=new Option("江津区","江津区");
		subTypeForm.options[16]=new Option("合川区","合川区");
		subTypeForm.options[17]=new Option("永川区","永川区");
		subTypeForm.options[18]=new Option("南川区","南川区");
		subTypeForm.options[19]=new Option("綦江县","綦江县");
		subTypeForm.options[20]=new Option("潼南县","潼南县");
		subTypeForm.options[21]=new Option("铜梁县","铜梁县");
		subTypeForm.options[22]=new Option("大足县","大足县");
		subTypeForm.options[23]=new Option("荣昌县","荣昌县");
		subTypeForm.options[24]=new Option("壁山县","壁山县");
		subTypeForm.options[25]=new Option("垫江县","垫江县");
		subTypeForm.options[26]=new Option("武隆县","武隆县");
		subTypeForm.options[27]=new Option("丰都县","丰都县");
		subTypeForm.options[28]=new Option("城口县","城口县");
		subTypeForm.options[29]=new Option("梁平县","梁平县");
		subTypeForm.options[30]=new Option("开县","开县");
		subTypeForm.options[31]=new Option("巫溪县","巫溪县");
		subTypeForm.options[32]=new Option("巫山县","巫山县");
		subTypeForm.options[33]=new Option("奉节县","奉节县");
		subTypeForm.options[34]=new Option("云阳县","云阳县");
		subTypeForm.options[35]=new Option("忠县","忠县");
		subTypeForm.options[36]=new Option("石柱","石柱");
		subTypeForm.options[37]=new Option("彭水","彭水");
		subTypeForm.options[38]=new Option("酉阳","酉阳");
		subTypeForm.options[39]=new Option("秀山","秀山");
                subTypeForm.options[0].selected=true;
		break;
	case "浙江"://浙江

		subTypeForm.options[0]=new Option("杭州","杭州");
		subTypeForm.options[1]=new Option("宁波","宁波");
		subTypeForm.options[2]=new Option("温州","温州");
		subTypeForm.options[3]=new Option("嘉兴","嘉兴");
		subTypeForm.options[4]=new Option("湖州","湖州");
		subTypeForm.options[5]=new Option("绍兴","绍兴");
		subTypeForm.options[6]=new Option("金华","金华");
		subTypeForm.options[7]=new Option("衢州","衢州");
		subTypeForm.options[8]=new Option("舟山","舟山");
		subTypeForm.options[9]=new Option("台州","台州");
		subTypeForm.options[10]=new Option("丽水","丽水");
		subTypeForm.options[0].selected=true;
		break;
	case "江西"://江西

		subTypeForm.options[0]=new Option("南昌","南昌");
		subTypeForm.options[1]=new Option("景德镇","景德镇");
		subTypeForm.options[2]=new Option("萍乡","萍乡");
		subTypeForm.options[3]=new Option("九江","九江");
		subTypeForm.options[4]=new Option("新余","新余");
		subTypeForm.options[5]=new Option("鹰潭","鹰潭");
		subTypeForm.options[6]=new Option("赣州","赣州");
		subTypeForm.options[7]=new Option("吉安","吉安");
		subTypeForm.options[8]=new Option("宜春","宜春");
		subTypeForm.options[9]=new Option("抚州","抚州");
		subTypeForm.options[10]=new Option("上饶","上饶");
		subTypeForm.options[0].selected=true;
		break;
	case "台湾"://台湾

		subTypeForm.options[0]=new Option("台北","台北");
		subTypeForm.options[1]=new Option("高雄","高雄");
		subTypeForm.options[2]=new Option("台中","台中");
		subTypeForm.options[3]=new Option("台南","台南");
		subTypeForm.options[4]=new Option("基隆","基隆");
		subTypeForm.options[5]=new Option("新竹","新竹");
		subTypeForm.options[0].selected=true;
		break;
	case "香港"://香港
		subTypeForm.options[0]=new Option("香港","香港");
		subTypeForm.options[0].selected=true;
		break;
	case "澳门"://澳门
		subTypeForm.options[0]=new Option("澳门","澳门");
		subTypeForm.options[0].selected=true;
		break;
	case "国外"://国外
		subTypeForm.options[0]=new Option("亚洲","亚洲");
		subTypeForm.options[1]=new Option("欧洲","欧洲");
		subTypeForm.options[2]=new Option("南美洲","南美洲");
		subTypeForm.options[3]=new Option("北美洲","北美洲");
		subTypeForm.options[4]=new Option("非洲","非洲");
		subTypeForm.options[5]=new Option("大洋洲","大洋洲");
		subTypeForm.options[0].selected=true;
		break;
	}
	break;}
return -1;
}


function SetCity(){
	p = document.personal.s1;
	c = document.personal.s2;
	update_city("中国",p.options[p.options.selectedIndex].value, c);




    var a=new Array(c.options.length);
    for(i = 0;i < c.options.length;i ++)
      a[i]=c.options[i];
    c.length = 0;

    c.options[0]=new Option("请选择","");
    for(i = 0; i < a.length;i ++)
      c.options[i+1]=a[i];

    


	c.options[0].selected=true;
	return ;

}


function SetCity(p,c){
	
	update_city("中国",p.options[p.options.selectedIndex].value, c);




    var a=new Array(c.options.length);
    for(i = 0;i < c.options.length;i ++)
      a[i]=c.options[i];
    c.length = 0;

    c.options[0]=new Option("请选择","");
    for(i = 0; i < a.length;i ++)
      c.options[i+1]=a[i];

    


	c.options[0].selected=true;
	return ;

}


