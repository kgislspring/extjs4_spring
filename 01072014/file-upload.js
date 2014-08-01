Ext.onReady(function(){
	
	/* The data store for EXTJS Combobox starts */
	var states = Ext.create('Ext.data.Store', {
	    fields: ['name', 'value'],
	    data:
	    [
	     {"name":"TN","value":"Tamilnadu"},
	     {"name":"Kerala","value":"Kerala"}
	    ]
	});
	/* Combobox data store for EXTJS Ends */
	
    Ext.create('Ext.form.Panel', {
        title: 'File Uploader',
        width: 400,
        bodyPadding: 10,
        frame: true,
        renderTo: 'fi-form',    
        items: [
                /* Code to load combobox starts */
        {
	        xtype: 'combo',
	        name:'state',
	        fieldLabel: 'Enter state ',
	        store: states,
	        displayField: 'value',
		    valueField: 'name',
	        allowBlank: true,
        },
                /* Code to load combobox Ends */
        {
            xtype: 'filefield',
            name: 'file1',
            fieldLabel: 'File1',
            labelWidth: 50,
            accept: ['xls'],  
            msgTarget: 'side',
            allowBlank: false,
            anchor: '100%',
            buttonText: 'Select a File...'
        },{
            xtype: 'filefield',
            name: 'file2',
            fieldLabel: 'File2',
            labelWidth: 50,
            msgTarget: 'side',
            anchor: '100%',
            buttonText: 'Select a File...'
        },
        {
            xtype: 'filefield',
            name: 'file3',
            fieldLabel: 'File3',
            labelWidth: 50,
            msgTarget: 'side',
            anchor: '100%',
            buttonText: 'Select a File...'
        },
        {
            xtype: 'filefield',
            name: 'file4',
            fieldLabel: 'File4',
            labelWidth: 50,
            msgTarget: 'side',
            anchor: '100%',
            buttonText: 'Select a File...'
        }
       
        ],

        buttons: [{
            text: 'Upload',
            handler: function() {
                var form = this.up('form').getForm();
                if(form.isValid()){
                    form.submit({
                        url: '/EXTJS4RestUpload/upload.do',
                        waitMsg: 'Exporting data to database...',
                        success: function(fp, o) {
                            Ext.Msg.alert('Success', 'Your file has been uploaded.');
                        },
                        failure: function(fp, o)
                        {
                        	Ext.Msg.alert('Error',"Invalid file format or column size..,Check column size is 5 and file format is .xls");
                        }
                    });
                }
            }
        }]
    });

});