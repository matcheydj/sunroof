Ext.namespace('Ext.ux.plugins');

Ext.ux.plugins.PageCycleResizer = Ext.extend(Object, {

  pageSizes: [5, 10, 15, 20, 25, 30, 50, 75, 100, 200, 300, 500],
  prependText: '每页显示',
  tipText: '行',
  iconCls: 'icon-app-add-delete',

  constructor: function(config){
    Ext.apply(this, config);
    Ext.ux.plugins.PageCycleResizer.superclass.constructor.call(this, config);
  },

  init : function(pagingToolbar) {

    var tt = this.tipText; var ic = this.iconCls;
    var items=[];
    Ext.iterate(this.pageSizes, function(ps) {
      items.push({
        text: " " + ps + tt,
        value: ps,
        iconCls:ic,
        checked: pagingToolbar.pageSize==ps ? true : false
      });
    });

    var button = Ext.apply(new Ext.CycleButton({
      showText: true,
      items: items,
      listeners: {
    	change: function(button, item) {
    	  pagingToolbar.pageSize = item.value;
    	  var rowIndex = 0;
    	  var gp = pagingToolbar.findParentBy (
    	    function (ct, cmp) {
    	      return (ct instanceof Ext.grid.GridPanel) ? true : false;
    	    }
    	  );
    	  var sm = gp.getSelectionModel();
    	  if (undefined != sm && sm.hasSelection()) {
    	    if (sm instanceof Ext.grid.RowSelectionModel) {
    	      rowIndex = gp.store.indexOf( sm.getSelected() ) ;
    	      } else if (sm instanceof Ext.grid.CellSelectionModel) {
    	        rowIndex = sm.getSelectedCell()[0] ;
    	      }
            }
    	    rowIndex += pagingToolbar.cursor;
    	    pagingToolbar.doLoad(Math.floor(rowIndex/pagingToolbar.pageSize)*pagingToolbar.pageSize);
          }
        }
      }), {prependText: this.prependText}
    );
    var inputIndex = pagingToolbar.items.indexOf(pagingToolbar.refresh);
    pagingToolbar.insert(++inputIndex,'-');
    pagingToolbar.insert(++inputIndex, button);
    pagingToolbar.on({
      beforedestroy: function() {
        button.destroy();
      }
    });
  }
});
