/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    config.image_previewText = ' '; //预览区域显示内容
    config.filebrowserImageUploadUrl = "../handler/upload_file.ashx"; //待会要上传的action或servlet
};
