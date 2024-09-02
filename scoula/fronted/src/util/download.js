import axios from 'axios';

export const downloadFile=async(fileUrl)=>{
    try{
        const link=document.createElement('a');
        link.href=fileUrl;

        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);

    }catch(error){
        console.error(error);
    }
};