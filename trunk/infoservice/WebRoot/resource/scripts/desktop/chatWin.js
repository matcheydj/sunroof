Ext.namespace('ExtFrame.ui');
ExtFrame.ui.ChatWin = function(_toPerId, _toUserName, _currentPerId, _currentUsername, _toTypeString, qtip) {
    var toPerId = _toPerId;
    var toUserName = _toUserName;
    var currentPerId = _currentPerId;
    var currentUsername = _currentUsername;
    var toTypeString = _toTypeString;
    var titleShow = qtip;

    var win = null;
    /**
     * @method creteWin
     * @public
     * @description create the ChatWin
     */
    var createWin = function() {
        win = new Ext.Window({
            title : '和[' + titleShow + ']聊天中',
            id : toPerId + toUserName,
            width : 550,
            height : 450,
            collapsible : true,
            labelAlign : 'left',
            labelWidth : 60,
            hideLabel : false,
            maximizable : false,
            border : false,
            layout : 'border',
            frame : true,
            items : [{
                region : 'center',
                border : 0,
                height : 150,
                xtype : 'panel',
                id : 'showMsg',
                layout : 'fit',
                autoScroll:true,
                bodyStyle : 'padding:10px;'
            }, {
                region : 'south',
                minHeight : 150,
                split : true,
                xtype : 'form',
                layout:'fit',
                border : false,
                hideLabels : true,
                bodyStyle : 'background:transparent;',
                height : 150,
                items : [
                    {
                        xtype : 'htmleditor',
                        id : 'editMsg',
                        fieldLabel : '',
                        name : 'body',
                        height:130,
                        allowBlank : false,
                        anchor : '0-50',
                        fontFamilies:['宋体','隶书','黑体','楷体_GB2312'],
                        style:'font-family:"宋体";',
                        defaultFont:'宋体',
                        border : true

                    }],
                buttons : [{
                    text : '发送',
                    handler : viewMsg
                }, {
                    text : '关闭',
                    handler : closeWin
                }]
            }]
        });
        win.show();
    }
    /**
     * @method sendMsg
     * @private
     * @description view your messages dynamicly
     */
    var viewMsg = function() {
        var message = this.ownerCt.form.items.items[0].getValue();
        var sendDate = new Date().format('Y-m-d H:i:s');
        var showPanel = this.ownerCt.ownerCt.getComponent(0);
        var formatMsg = "<div class='_msgtitle' style='color:blue'>"
                + currentUsername + " " + sendDate + "</div><div class='_msg'>"
                + message + "</div>";
        showPanel.body.insertHtml("beforeEnd", formatMsg);
        showPanel.body.scroll('bottom', 9999);
        this.ownerCt.form.items.items[0].reset();
        submitMsg(message);
    }
    /**
     * @method closeWin
     * @private
     * @description close the chatWin
     */
    var closeWin = function() {
        Ext.getCmp(toPerId + toUserName).close();
    }
    /**
     * @method submitMsg
     * @param message
     * @private
     * @description submit the messages to the server
     */
    var submitMsg = function(message) {
        var conn = new Ext.data.Connection();
        conn.request({
            url : '../userMessage/sendMSG.action',
            method : 'POST',
            params : {
                fromPerId:currentPerId,
                toPerId:toPerId,
                message : message,
                typeString:toTypeString
            },
            sucess : function(responseObject) {
                Ext.Msg.alert('发送成功', responseObject.statusText);
            },
            failure : function(responseObject) {
                Ext.Msg.alert('发送失败', responseObject.statusText);
            }
        });
    }
    return {
        /**
         * @method init
         * @public
         * @description initializes the win
         */
        init : function() {
            if (!win) {
                createWin();
            } else
                win.show();
        }
    }
}