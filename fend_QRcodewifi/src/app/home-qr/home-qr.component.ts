import { Component } from '@angular/core';
import { ApiService } from '../_service/api.service';
import { DomSanitizer } from '@angular/platform-browser';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-home-qr',
  templateUrl: './home-qr.component.html',
  styleUrl: './home-qr.component.css'
})

export class HomeQrComponent {

  form: any ={ network: null, password: null, type: null };
  network: String = '';
  password: String = '';
  type: String = "WPA";
  qrCode: any = '';
  success: any = null ;
  errorMess: String = '';


  constructor( private service: ApiService, private sanit: DomSanitizer) { }


  generateCode() { 
    
    this.service.creatorQRC(this.network, this.password, this.type).subscribe({
      next: data =>{
        this.qrCode = 'data:image/png;base64, '+ data.body;
        if(data)
          this.success = true;
      },
      error: err =>{
        this.errorMess = err.message;
        if(err)
          this.success = false;
      }
    });
  }

  downloadQRCode(){

    const byteChar = atob(this.qrCode.split(',')[1]);
    const byteNum = new Array(byteChar.length);
    for (let i = 0; i < byteChar.length; i++) 
      byteNum[i] = byteChar.charCodeAt(i);
    const byteArray = new Uint8Array(byteNum);
    const blob = new Blob([byteArray], { type: 'image/png' });
    saveAs(blob, 'QR_Code.png');    
  }
}


