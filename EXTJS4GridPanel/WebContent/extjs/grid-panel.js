Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux', '/EXTJS4GridPanel/extjs/ux/');
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ux.PreviewPlugin',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function(){
	Ext.tip.QuickTipManager.init();
	    
	Ext.define('DataGrid', {
	    extend: 'Ext.data.Model',
	    fields: [
	        'title', 'forumtitle', 'forumid', 'username',
	        {name: 'sno', type: 'int'},
	        {name: 'name',type: 'string'},
	        {name:'address',type:'string'},
	        {name:'mobile',type:'string'},
	        {name:'email',type:'string'}
	    ]
	});

	// create the Data Store
	var store = Ext.create('Ext.data.Store', {
	    pageSize: 50,
	    model: 'DataGrid',
	    remoteSort: true,
	    proxy: {
	         type: 'ajax',
	        url: '/EXTJS4GridPanel/griddata.do',
	        reader: {
	            root: 'users',
	            type:'json'        
	        },
	        simpleSortMode: true
	    }
	});

	    var grid = Ext.create('Ext.grid.Panel', {
	        width: 650,
	        height: 300,
	        title: 'EXTJS Data Grid Demo',
	        store: store,
	        disableSelection: true,
	        loadMask: true,
	        viewConfig: {
	            plugins: [{
	                ptype: 'preview',
	                bodyField: 'excerpt',
	                expanded: true,
	                pluginId: 'preview'
	            }]
	        },
	        // grid columns
	        columns:[{
	            text: "Sno",
	            dataIndex: 'sno',
	            width:50,
	            align: 'right',
	            sortable: false
	        },{
	            text: "Name",
	            dataIndex: 'name',
	            width: 150,
	            sortable: true
	        },{
	            text: "Address",
	            dataIndex: 'address',
	            width: 150,
	            sortable: true
	        },{
	            text: "Mobile",
	            dataIndex: 'mobile',
	            width: 150,
	            sortable: true
	        },{
	            text: "Email",
	            dataIndex: 'email',
	            width: 150,
	            sortable: true
	        }],
	        renderTo: 'frm-grid'
	    });

	    // trigger the data store load
	    store.loadPage(1);
   
});






