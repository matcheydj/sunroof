/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


// Sample desktop configuration
MyDesktop = new Ext.app.App({
	init :function(){
		Ext.QuickTips.init();
	},

	getModules : function(){
		return [
			new MyDesktop.GridWindow(),
            new MyDesktop.TabWindow(),
            new MyDesktop.AccordionWindow(),
            new MyDesktop.SecurityWindow(),
            new MyDesktop.BogusMenuModule(),
            new MyDesktop.BogusModule()
		];
	},

    // config for the start menu
    getStartConfig : function(){
        return {
            title: 'BMS',
            iconCls: 'user',
            toolItems: [{
                text:'Settings',
            //    cls:'icon_location',
                cls:'settings',
                scope:this
            },'-',{
                text:'Logout',
                cls:'logout',
                scope:this
            }]
        };
    }
});



/*
 *  windows
 */
MyDesktop.GridWindow = Ext.extend(Ext.app.Module, {
    id:'grid-win',
    init : function(){
        this.launcher = {
            text: '列表窗口',
            cls:'icon-grid',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-win');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-win',
                title:'列表窗口',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                shim:false,
                animCollapse:false,
                constrainHeader:true,

                layout: 'fit',
                items:
                    new Ext.grid.GridPanel({
                        border:false,
                        ds: new Ext.data.Store({
                            reader: new Ext.data.ArrayReader({}, [
                               {name: 'company'},
                               {name: 'price', type: 'float'},
                               {name: 'change', type: 'float'},
                               {name: 'pctChange', type: 'float'}
                            ]),
                            data: Ext.grid.dummyData
                        }),
                        cm: new Ext.grid.ColumnModel([
                            new Ext.grid.RowNumberer(),
                            {header: "Company", width: 120, sortable: true, dataIndex: 'company'},
                            {header: "Price", width: 70, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},
                            {header: "Change", width: 70, sortable: true, dataIndex: 'change'},
                            {header: "% Change", width: 70, sortable: true, dataIndex: 'pctChange'}
                        ]),

                        viewConfig: {
                            forceFit:true
                        },
                        //autoExpandColumn:'company',

                        tbar:[{
                            text:'Add Something',
                            tooltip:'Add a new row',
                            iconCls:'add'
                        }, '-', {
                            text:'Options',
                            tooltip:'Blah blah blah blaht',
                            iconCls:'option'
                        },'-',{
                            text:'Remove Something',
                            tooltip:'Remove the selected item',
                            iconCls:'remove'
                        }]
                    })
            });
        }
        win.show();
    }
});

MyDesktop.SecurityWindow = Ext.extend(Ext.app.Module,{
	id:'security-win',
	init:function(){
		this.launcher = {
            text: '权限控制',
            cls:'tabs',
            handler : this.createWindow,
            scope: this
        }
	},
	createWindow:function(){
		var sm = new Ext.grid.CheckboxSelectionModel();
		var desktop = this.app.getDesktop();
        var win = desktop.getWindow('security-win');
        var gridStore = new Ext.data.Store({
                       proxy : new Ext.data.ScriptTagProxy({
    							url : 'ugroup/ugroup.shtml'
							}),
                       reader: new Ext.data.JsonReader({
                       root : 'results',
			           totalProperty : 'total',
			           id : 'id'
			       },[
			       	{
			            name : 'code',
			            mapping : 'code'
			        },
			        {
			            name : 'name',
			            mapping : 'name'
			        },
			        {
			            name : 'isLocked',
			            mapping : 'isLocked'
			        },
			        {
			            name : 'describe',
			            mapping : 'describe'
			        }
			       ]),remoteSort : false
            	});
        var grid =  new Ext.grid.GridPanel({
                       border:false,
                       store: gridStore,
//                       loadMask : {
//						msg : '正在加载数据，请稍侯……'
//					   },
                       sm:sm,
                       cm: new Ext.grid.ColumnModel([
                           sm,
                           {header: "用户组名称", width: 120, sortable: true, dataIndex: 'name',css: 'white-space:normal;border:#eee solid;border-width:0 0 0 1;'},
                           {header: "启用状态", width: 70, sortable: true,  dataIndex: 'isLocked',renderer:function(value){if("1"==value) return "<font color='green'>已启用</font>";else return "<font color='red'>已锁定</font>"},css: 'white-space:normal;border:#eee solid;border-width:0 0 0 1;'},
                           {header: "编码", width: 70, sortable: true, dataIndex: 'code',css: 'white-space:normal;border:#eee solid;border-width:0 0 0 1;'},
                           {header: "描述", width: 70, sortable: true, dataIndex: 'describe',css: 'white-space:normal;border:#eee solid;border-width:0 0 0 1;'}
                       ]),

                       viewConfig: {
                           forceFit:true
                       },
                       stripeRows : true,
                       //autoExpandColumn:'company',
					bodyStyle : 'width:100%;height:395px',
                       tbar:[{
                           text:'添加用户组',
                           tooltip:'Add a new group',
                           iconCls:'security-add',
                           handler:function(){
                           		var win,panel;
                           		var name = new Ext.form.TextField({
										        fieldLabel: '用户组名称',
										        id:'name',
										        name: 'secGp.name',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        maxLengthText:"最大长度18"
										    });
								var code = new Ext.form.TextField({
										        fieldLabel: '用户组编码',
										        id:'code',
										        name: 'secGp.code',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        maxLengthText:"最大长度18"
										    });
								var describe = new Ext.form.TextField({
										        fieldLabel: '用户组描述',
										        id:'describe',
										        name: 'secGp.describe',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        maxLengthText:"最大长度18"
										    });
								if (panel != null) {
									panel.destroy(true);
									panel = null;
								}
								if (!panel) {
									panel = new Ext.FormPanel({
												labelWidth : 100,
												labelAlign : 'right',
												url : 'ugroup/saveGroup.shtml',
												frame : true,
												bodyStyle : 'padding:5px 5px 0',
												items : [name,code,describe]
											});
								}
								if (win != null) {
									win.destroy(true);
									win = null;
								}
								if (!win) {
									win = new Ext.Window({
											id : 'win',
											title : '用户组添加',
											width : 300,
											height : 180,
											minWidth : 200,
											minHeight : 150,
											xtype : 'window',
											maximizable : false,
											closeAction : 'close',
											layout : 'fit',
											plain : true,
											closable : true,
											modal : true,
											bodyStyle : 'padding:5px;',
											buttonAlign : 'center',
											items : panel,
											buttons : [{
												handler : function() {
													if (panel.getForm().isValid()) {
														panel.getForm().submit({
															waitMsg : '正在处理，请稍等...',
															reset : true,
															failure : function(form, action) {
																Ext.MessageBox.hide();
																Ext.Msg.show({
																	title : '错误',
																	msg : '<span style="text-align:center;width:150px;margin:5 0 5 0;">'
																			+ '保存失败！'
																			+ '</span>',
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.WARNING
																});
															},
															success : function(form, action) {
																Ext.MessageBox.hide();
																Ext.Msg.show({
																	title : '成功',
																	msg : '<span style="text-align:center;width:150px;margin:5 0 5 0;">'
																			+ action.result.msg
																			+ '</span>',
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.INFO
																});
																gridStore.reload();
																win.hide();
															},
															method : 'POST',
															params : {
															}
														});
													}
												},
												text : '保存'
											}, {
												handler : function() {
													win.close();
												},
												text : '取消'
											}]
										});
							}
							win.show();
                           }
                       }, '-', {
                           text:'修改用户组',
                           tooltip:'modify a group',
                           iconCls:'security-modify',
                           handler:function(){
                           var selectedCount = sm.getCount();
                           if (selectedCount == 0) {
								Ext.Msg.alert('修改用户组', '未选中要修改的数据，请选择要修改的数据！');
								return;
						   }
						   else if (selectedCount > 1) {
								Ext.Msg.alert('修改用户组', '只能选择一条数据，不能同时选择多条数据！');
								return;
						   }
						   else{
                           		var win,panel;
                           		var record = sm.getSelected();
								var id = record.id;
								var nameValue = gridStore.getById(id).data['name'];
								var codeValue = gridStore.getById(id).data['code'];
								var describeValue = gridStore.getById(id).data['describe'];
								var rid = new Ext.form.Hidden({
									name : 'secGp.id',
									fieldLabel : '主键',
									maxLength : 18,
									maxLengthText : "最大长度18",
									allowBlank : false,
									value : id,
									width : 100
								});
                           		var name = new Ext.form.TextField({
										        fieldLabel: '用户组名称',
										        id:'name_modify',
										        name: 'secGp.name',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        value : nameValue,
										        maxLengthText:"最大长度18"
										    });
								var code = new Ext.form.TextField({
										        fieldLabel: '用户组编码',
										        id:'code_modify',
										        name: 'secGp.code',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        value : codeValue,
										        maxLengthText:"最大长度18"
										    });
								var describe = new Ext.form.TextField({
										        fieldLabel: '用户组描述',
										        id:'describe_modify',
										        name: 'secGp.describe',
										        width:120,
										        maxLength:18,
										        allowBlank:true,
										        value : describeValue,
										        maxLengthText:"最大长度18"
										    });
								if (panel != null) {
									panel.destroy(true);
									panel = null;
								}
								if (!panel) {
									panel = new Ext.FormPanel({
												labelWidth : 100,
												labelAlign : 'right',
												url : 'ugroup/saveGroup.shtml',
												frame : true,
												bodyStyle : 'padding:5px 5px 0',
												items : [rid,name,code,describe]
											});
								}
								if (win != null) {
									win.destroy(true);
									win = null;
								}
								if (!win) {
									win = new Ext.Window({
											id : 'win',
											title : '用户组修改',
											width : 300,
											height : 180,
											minWidth : 200,
											minHeight : 150,
											xtype : 'window',
											maximizable : false,
											closeAction : 'close',
											layout : 'fit',
											plain : true,
											closable : true,
											modal : true,
											bodyStyle : 'padding:5px;',
											buttonAlign : 'center',
											items : panel,
											buttons : [{
												handler : function() {
													if (panel.getForm().isValid()) {
														panel.getForm().submit({
															waitMsg : '正在处理，请稍等...',
															reset : true,
															failure : function(form, action) {
																Ext.MessageBox.hide();
																Ext.Msg.show({
																	title : '错误',
																	msg : '<span style="text-align:center;width:150px;margin:5 0 5 0;">'
																			+ action.result.msg
																			+ '</span>',
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.WARNING
																});
															},
															success : function(form, action) {
																Ext.MessageBox.hide();
																Ext.Msg.show({
																	title : '成功',
																	msg : '<span style="text-align:center;width:150px;margin:5 0 5 0;">'
																			+ action.result.msg
																			+ '</span>',
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.INFO
																});
																gridStore.reload();
																win.close();
															},
															method : 'POST',
															params : {
															}
														});
													}
												},
												text : '保存'
											}, {
												handler : function() {
													win.close();
												},
												text : '取消'
											}]
										});
							}
							win.show();
							}
                           }
                       }, '-', {
                           text:'移除用户组',
                           tooltip:'remove groups',
                           iconCls:'security-remove',
                           handler:function(){
                           	   var selectedCount = sm.getCount();
	                           if (selectedCount == 0) {
									Ext.Msg.alert('删除用户组', '未选中要删除的数据，请选择要删除的数据！');
									return;
							   }else{
							   		Ext.Msg.confirm('删除用户组', '请确认要删除数据吗?按是将删除选中数据！', function(btn) {
							   			if (btn == 'yes') {
							   				var records = sm.getSelections();
											var _ids = '';
											for (var i = 0; i < records.length; i++) {
												_ids += records[i].id;
												_ids += '_';
											}
											Ext.MessageBox.wait('正在处理，请稍等...');
											Ext.Ajax.request({
												url : 'ugroup/deleteGroup.shtml',
												failure : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '错误',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '删除失败！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.WARNING
													});
												},
												success : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '成功',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '删除成功！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
													});
													gridStore.reload();
												},
												params : {
													deleteID : _ids
												}
											});
							   			}
							   		})
							   }
                           }
                       }, '-', {
                           text:'锁定用户组',
                           tooltip:'lock groups',
                           iconCls:'security-lock',
                           handler:function(){
                           	   var selectedCount = sm.getCount();
	                           if (selectedCount == 0) {
									Ext.Msg.alert('锁定用户组', '未选中要锁定的数据，请选择要锁定的数据！');
									return;
							   }else{
							   		Ext.Msg.confirm('锁定用户组', '请确认要锁定数据吗?按是将锁定选中数据！', function(btn) {
							   			if (btn == 'yes') {
							   				var records = sm.getSelections();
											var _ids = '';
											for (var i = 0; i < records.length; i++) {
												_ids += records[i].id;
												_ids += '_';
											}
											Ext.MessageBox.wait('正在处理，请稍等...');
											Ext.Ajax.request({
												url : 'ugroup/lockGroup.shtml',
												failure : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '错误',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '锁定失败！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.WARNING
													});
												},
												success : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '成功',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '锁定成功！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
													});
													gridStore.reload();
												},
												params : {
													deleteID : _ids,
													lockType:"2"
												}
											});
							   			}
							   		})
							   }
                           }
                       }, '-', {
                           text:'解锁用户组',
                           tooltip:'unlock groups',
                           iconCls:'security-unlock',
                                handler:function(){
                           	   var selectedCount = sm.getCount();
	                           if (selectedCount == 0) {
									Ext.Msg.alert('解锁用户组', '未选中要锁定的数据，请选择要解锁的数据！');
									return;
							   }else{
							   		Ext.Msg.confirm('解锁用户组', '请确认要锁定数据吗?按是将解锁选中数据！', function(btn) {
							   			if (btn == 'yes') {
							   				var records = sm.getSelections();
											var _ids = '';
											for (var i = 0; i < records.length; i++) {
												_ids += records[i].id;
												_ids += '_';
											}
											Ext.MessageBox.wait('正在处理，请稍等...');
											Ext.Ajax.request({
												url : 'ugroup/lockGroup.shtml',
												failure : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '错误',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '解锁失败！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.WARNING
													});
												},
												success : function(result, request) {
													Ext.MessageBox.hide();
													Ext.Msg.show({
														title : '成功',
														msg : '<span style="text-align:center;width:250px;margin:5 0 5 0;">'
																+ '解锁成功！' + '</span>',
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
													});
													gridStore.reload();
												},
												params : {
													deleteID : _ids,
													lockType:"1"
												}
											});
							   			}
							   		})
							   }
                           }
                       },'-',{
                           text:'权限维护',
                           tooltip:'grant rights to groups',
                           iconCls:'security-rights'
                       }]
                   })
        if(!win){
            win = desktop.createWindow({
                id: 'security-win',
                title:'权限控制',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                //maximizable: false,
                resizable:false,
                shim:true,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items:
                	 new Ext.TabPanel({
                        activeTab:0,
                        items: [{
                            title: '用户组设置',
                            header:false,
                            border:false,
                            bodyStyle : 'width:100%;height:100%',
                            items:
		                       grid
                        },{
                            title: '菜单设置',
                            header:false,
                            html : '<p>here will use jquery plugin -- ztree</p>',
                            border:false
                        }]
                    })
                
                   
            });
        }
        win.show();
        gridStore.load();
	}
});



MyDesktop.TabWindow = Ext.extend(Ext.app.Module, {
    id:'tab-win',
    init : function(){
        this.launcher = {
            text: '多标签窗口',
            cls:'tabs',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('tab-win');
        if(!win){
            win = desktop.createWindow({
                id: 'tab-win',
                title:'多标签窗口',
                width:740,
                height:480,
                iconCls: 'tabs',
                shim:false,
                animCollapse:false,
                border:false,
                constrainHeader:true,

                layout: 'fit',
                items:
                    new Ext.TabPanel({
                        activeTab:0,

                        items: [{
                            title: 'Tab Text 1',
                            header:false,
                            html : '<p>123</p>',
                            border:false
                        },{
                            title: 'Tab Text 2',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            border:false
                        },{
                            title: 'Tab Text 3',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            border:false
                        },{
                            title: 'Tab Text 4',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            border:false
                        }]
                    })
            });
        }
        win.show();
    }
});


//IM 聊天窗口
    var treeDoubleClick1 = function(node, e) {
        if (node.attributes.perId == '') {
            return null;
        }
        new ExtFrame.ui.ChatWin(node.attributes.perId, node.text, '1', 'admin', 'PER', node.attributes.qtip + '—' + node.text).init();
    }


MyDesktop.AccordionWindow = Ext.extend(Ext.app.Module, {
    id:'acc-win',
    init : function(){
        this.launcher = {
            text: '即时聊天',
            cls:'accordion',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('acc-win');
        if(!win){
            win = desktop.createWindow({
                id: 'acc-win',
                title: '即时聊天',
                width:250,
                height:400,
                iconCls: 'accordion',
                shim:false,
                animCollapse:false,
                constrainHeader:true,

             /*   tbar:[{
                    tooltip:{title:'Rich Tooltips', text:'Let your users know what they can do!'},
                    iconCls:'connect'
                },'-',{
                    tooltip:'Add a new user',
                    iconCls:'user-add'
                },' ',{
                    tooltip:'Remove the selected user',
                    iconCls:'user-delete'
                }],*/

                layout:'accordion',
                border:false,
                layoutConfig: {
                    animate:false
                },

                items: [
                    new Ext.tree.TreePanel({
                    id:'im-tree',
                    title: '在线管理员',
                    loader: new Ext.tree.TreeLoader({
                        dataUrl:'onlineUser.shtml'
                    }),
                    rootVisible:false,
                    lines:false,
                    autoScroll:true,
                    tools:[{
                        id:'refresh',
                        on:{
                            click: function() {
                                var tree = Ext.getCmp('im-tree');
                                tree.body.mask('Loading', 'x-mask-loading');
                                tree.root.reload();
                                tree.root.collapse(true, false);
                                setTimeout(function() {
                                    tree.body.unmask();
                                    tree.root.expand(true, true);
                                }, 1000);
                            }
                        }
                    }],
                    root: new Ext.tree.AsyncTreeNode({
                        text:'在线管理员',
                        id:'0'
                    }),
                    listeners: {
                        'dblclick':{fn:treeDoubleClick1}
                    }
                }),{
                        title: '所有管理员',
                        html : '<p>Something useful would be in here.</p>'
                    }/*, {
                        title: '设置',
                        html:'<p>Something useful would be in here.</p>',
                        autoScroll:true
                    }*/
                ]
            });
        }
        win.show();
    }
});

// for example purposes
var windowIndex = 0;

MyDesktop.BogusModule = Ext.extend(Ext.app.Module, {
    init : function(){
        this.launcher = {
            text: 'Window '+(++windowIndex),
            cls:'bogus',
            handler : this.createWindow,
            scope: this,
            windowId:windowIndex
        }
    },

    createWindow : function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus'+src.windowId);
        if(!win){
            win = desktop.createWindow({
                id: 'bogus'+src.windowId,
                title:src.text,
                width:640,
                height:480,
                html : '<p>Something useful would be in here.</p>',
                //iconCls: 'bogus',
                cls:'bogus',
                shim:false,
                animCollapse:false,
                constrainHeader:true
            });
        }
        win.show();
    }
});


MyDesktop.BogusMenuModule = Ext.extend(MyDesktop.BogusModule, {
    init : function(){
        this.launcher = {
            text: 'Bogus Submenu',
            //iconCls: 'bogus',
            cls: 'bogus',
            handler: function() {
				return false;
			},
            menu: {
                items:[{
                    text: 'Bogus Window '+(++windowIndex),
                    //iconCls:'bogus',
                    cls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: 'Bogus Window '+(++windowIndex),
                    cls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: 'Bogus Window '+(++windowIndex),
                    cls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: 'Bogus Window '+(++windowIndex),
                    cls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: 'Bogus Window '+(++windowIndex),
                    cls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                }]
            }
        }
    }
});


// Array data for the grid
Ext.grid.dummyData = [
    ['3m Co',71.72,0.02,0.03,'9/1 12:00am'],
    ['Alcoa Inc',29.01,0.42,1.47,'9/1 12:00am'],
 /*   ['American Express Company',52.55,0.01,0.02,'9/1 12:00am'],
    ['American International Group, Inc.',64.13,0.31,0.49,'9/1 12:00am'],
    ['AT&T Inc.',31.61,-0.48,-1.54,'9/1 12:00am'],
    ['Caterpillar Inc.',67.27,0.92,1.39,'9/1 12:00am'],
    ['Citigroup, Inc.',49.37,0.02,0.04,'9/1 12:00am'],
    ['Exxon Mobil Corp',68.1,-0.43,-0.64,'9/1 12:00am'],
    ['General Electric Company',34.14,-0.08,-0.23,'9/1 12:00am'],
    ['General Motors Corporation',30.27,1.09,3.74,'9/1 12:00am'],
    ['Hewlett-Packard Co.',36.53,-0.03,-0.08,'9/1 12:00am'],
    ['Honeywell Intl Inc',38.77,0.05,0.13,'9/1 12:00am'],
    ['Intel Corporation',19.88,0.31,1.58,'9/1 12:00am'],
    ['Johnson & Johnson',64.72,0.06,0.09,'9/1 12:00am'],
    ['Merck & Co., Inc.',40.96,0.41,1.01,'9/1 12:00am'],
    ['Microsoft Corporation',25.84,0.14,0.54,'9/1 12:00am'],
    ['The Coca-Cola Company',45.07,0.26,0.58,'9/1 12:00am'],
    ['The Procter & Gamble Company',61.91,0.01,0.02,'9/1 12:00am'],
    ['Wal-Mart Stores, Inc.',45.45,0.73,1.63,'9/1 12:00am'],*/
    ['Walt Disney Company (The) (Holding Company)',29.89,0.24,0.81,'9/1 12:00am']
];