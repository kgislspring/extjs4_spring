Ext.onReady(function(){
	
    Ext.create('Ext.form.Panel', {
        title: 'File Uploader',
        width: 400,
        bodyPadding: 10,
        frame: true,
        renderTo: 'fi-form',    
        items: [{
            xtype: 'filefield',
            name: 'file',
            fieldLabel: 'File',
            labelWidth: 50,
            msgTarget: 'side',
            allowBlank: false,
            anchor: '100%',
            buttonText: 'Select a File...'
        }],

        buttons: [{
            text: 'Upload',
            handler: function() {
                var form = this.up('form').getForm();
                if(form.isValid()){
                    form.submit({
                        url: '/EXTJS4FileUpload/upload.do',
                        waitMsg: 'Exporting data to database...',
                        success: function(fp, o) {
                            Ext.Msg.alert('Success', 'Your file has been Exported.');
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