package com.grape.learnmsp.activity;




import com.grape.learnmsp.R;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Disclaimer extends Activity
{
	Button b1;
	public static String sendprogram;
	public static String offset;
	
	private String disclaim = "/* --COPYRIGHT--,BSD_EX\n" +
			"* Copyright (c) 2012, Texas Instruments Incorporated\n" +
			"* All rights reserved." +
			"\n" +
			"* Redistribution and use in source and binary forms, with or without\n" +
			"* modification, are permitted provided that the following conditions\n" +
			"* are met:\n" +
			"*\n" +
			"* *  Redistributions of source code must retain the above copyright\n" +
			"*    notice, this list of conditions and the following disclaimer.\n" +
			"*\n" +
			"* *  Redistributions in binary form must reproduce the above copyright\n" +
			"*    notice, this list of conditions and the following disclaimer in the\n" +
			"*    documentation and/or other materials provided with the distribution.\n" +
			"*\n" +
			"* *  Neither the name of Texas Instruments Incorporated nor the names of\n" +
			"*    its contributors may be used to endorse or promote products derived\n" +
			"*    from this software without specific prior written permission.\n" +
			"*\n" +
			"* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" \n" +
					"* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,\n" +
					"* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR\n" +
					"* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR\n" +
					"* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,\n" +
					"* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,\n" +
					"* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;\n" +
					"* OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,\n" +
					"* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR\n" +
					"* OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,\n" +
					"* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n" +
					"*\n" +
					"*******************************************************************************\n" +
					"* \n" +
					"*     MSP430 CODE EXAMPLE DISCLAIMER\n" +
					"*\n" +
					"* MSP430 code examples are self-contained low-level programs that typically\n" +
					"* demonstrate a single peripheral function or device feature in a highly\n" +
					"* concise manner. For this the code may rely on the device's power-on default\n" +
					"* register values and settings such as the clock configuration and care must\n" +
					"* be taken when combining code from several examples to avoid potential side\n" +
					"* effects. Also see www.ti.com/grace for a GUI- and www.ti.com/msp430ware\n" +
					"* for an API functional library-approach to peripheral configuration.\n" +
					"*\n" +
					"* --/COPYRIGHT--*/  \n\n\n\n" +
					"//  M. Morales\n" +
					"//  Texas Instruments Inc.\n" +
					"//  June 2009\n" +
					"//  Built with CCE v3.1 Build 3.2.3.6.4 & IAR Embedded Workbench Version: 4.11B\n" +
					"//******************************************************************************\n" +
					"//   F. Chen\n" +
					"//   Texas Instruments Inc.\n" +
					"//   Dec. 201\n" +
					"//   Built with CCS Version: 5.2.1 and IAR Embedded Workbench Version: 5.51.1\n" +
					"//******************************************************************************\n" +
					"//   P. Thanigai\n" +
					"//   Texas Instruments Inc.\n" +
					"//   June 2009\n" +
					"//   Built with CCE Version: 3.2.2 and IAR Embedded Workbench Version: 4.11B\n" +
					"//******************************************************************************\n";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.disclaimer);
		
		TextView tv,tva;

		tva = (TextView)findViewById(R.id.textView1);
		tv = (TextView)findViewById(R.id.textView3);
		
		String fontpath = "fonts/Slabo27px-Regular.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontpath);
		
		tv.setTypeface(tf);
		tva.setTypeface(tf);
		tv.setText(disclaim);
		
		b1 = (Button)findViewById(R.id.button1);
		final Intent i = getIntent();
		
		if(i.hasExtra("c1"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c1"));
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c1";
			
		}
		
		if(i.hasExtra("c2"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c2"));
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c2";
			
		}
		
		if(i.hasExtra("c3"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c3"));
			//tva.setText("Find the largest number");
			AdBuddiz.cacheAds(this); offset="c3";
			
		}
		
		if(i.hasExtra("c4"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c4"));
			//tva.setText("Find the smallest number");
			AdBuddiz.cacheAds(this); offset="c4";
			
		}
		
		if(i.hasExtra("c5"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c5"));
			//tva.setText("Sort data in ascending order");
			AdBuddiz.cacheAds(this); offset="c5";
			
		}
		
		if(i.hasExtra("c6"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c6"));
			//tva.setText("Sort data in descending order");
			AdBuddiz.cacheAds(this); offset="c6";
			
		}
		
		if(i.hasExtra("c7"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c7"));
			//tva.setText("Add 2 bytes");
			AdBuddiz.cacheAds(this); offset="c7";
			
		}
		
		if(i.hasExtra("c8"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c8"));
			//tva.setText("Add 2 words");
			AdBuddiz.cacheAds(this); offset="c8";
			
		}
		
		if(i.hasExtra("c9"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c9"));
			//tva.setText("Subtract 2 bytes");
			AdBuddiz.cacheAds(this); offset="c9";
			
		}
		
		if(i.hasExtra("c10"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c10"));
			//tva.setText("Subtract 2 words");
			AdBuddiz.cacheAds(this); offset="c10";
			
		}
		
		if(i.hasExtra("c11"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c11"));
			//tva.setText("Multiply 2 bytes");
			AdBuddiz.cacheAds(this); offset="c11";
			
		}
		
		if(i.hasExtra("c12"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c12"));
			//tva.setText("Multiply byte with word");
			AdBuddiz.cacheAds(this); offset="c12";
			
		}
		
		if(i.hasExtra("c13"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c13"));
			//tva.setText("Multiply 2 words");
			AdBuddiz.cacheAds(this); offset="c13";
			
		}
		
		if(i.hasExtra("c14"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c14"));
			//tva.setText("Square a number");
			AdBuddiz.cacheAds(this); offset="c14";
			
		}
		
		if(i.hasExtra("c15"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c15"));
			//tva.setText("Cube a number");
			AdBuddiz.cacheAds(this); offset="c15";
			
		}
		
		if(i.hasExtra("c16"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c16"));
			//tva.setText("Division of 2 bytes");
			AdBuddiz.cacheAds(this); offset="c16";
			
		}
		
		if(i.hasExtra("c17"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c17"));
			//tva.setText("Check for oddness");
			AdBuddiz.cacheAds(this); offset="c17";
			
		}
		
		if(i.hasExtra("c18"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c18"));
			//tva.setText("Check for positiveness");
			AdBuddiz.cacheAds(this); offset="c18";
			
		}
		
		if(i.hasExtra("c19"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c19"));
			//tva.setText("Count 1's and 0's in a number");
			AdBuddiz.cacheAds(this); offset="c19";
			
		}
		
		if(i.hasExtra("c20"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c20"));
			//tva.setText("Generate Fibonacci Series");
			AdBuddiz.cacheAds(this); offset="c20";
			
		}
		
		if(i.hasExtra("c21"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c21"));
			//tva.setText("ASCII to unpacked BCD");
			AdBuddiz.cacheAds(this); offset="c21";
			
		}
		
		if(i.hasExtra("c22"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c22"));
			//tva.setText("Unpacked BCD to ASCII");
			AdBuddiz.cacheAds(this); offset="c22";
			
		}
		
		if(i.hasExtra("c23"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c23"));
			//tva.setText("Packed BCD to ASCII");
			AdBuddiz.cacheAds(this); offset="c23";
			
		}
		
		if(i.hasExtra("c24"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c24"));
			//tva.setText("ASCII to packed BCD");
			AdBuddiz.cacheAds(this); offset="c24";
			
		}
		
		if(i.hasExtra("c25"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c25"));
			//tva.setText("Hexadecimal to Decimal");
			AdBuddiz.cacheAds(this); offset="c25";
			
		}
		
		if(i.hasExtra("c26"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c26"));
			//tva.setText("Decimal to Hexadecimal");
			AdBuddiz.cacheAds(this); offset="c26";
			
		}
		
		if(i.hasExtra("c27"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c27"));
			//tva.setText("HEX up/down count");
			AdBuddiz.cacheAds(this); offset="c27";
			
		}
		
		if(i.hasExtra("c28"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c28"));
			//tva.setText("BCD up count");
			AdBuddiz.cacheAds(this); offset="c28";
			
		}
		
		if(i.hasExtra("c29"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c29"));
			//tva.setText("BCD down count");
			AdBuddiz.cacheAds(this); offset="c29";
			
		}
		
		if(i.hasExtra("c30"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c30"));
			//tva.setText("GCD and LCM of 2 numbers");
			AdBuddiz.cacheAds(this); offset="c30";
			
		}
		
		if(i.hasExtra("c31"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c31"));
			//tva.setText("Factorial of a number");
			AdBuddiz.cacheAds(this); offset="c31";
			
		}
		
		if(i.hasExtra("c32"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c32"));
			//tva.setText("Generate square wave - 1");
			AdBuddiz.cacheAds(this); offset="c32";
			
		}
		
		if(i.hasExtra("c33"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c33"));
			//tva.setText("Generate square wave - 2");
			AdBuddiz.cacheAds(this); offset="c33";
			
		}
		
		if(i.hasExtra("c34"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c34"));
			//tva.setText("Serially send a byte");
			AdBuddiz.cacheAds(this); offset="c34";
			
		}
		
		if(i.hasExtra("c35"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c35"));
			//tva.setText("Realize a Boolean Expression");
			AdBuddiz.cacheAds(this); offset="c35";
			
		}
		
		
		if(i.hasExtra("c36"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c36"));
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c36";
			
		}
		
		if(i.hasExtra("c37"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c37"));
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c37";
			
		}
		
		if(i.hasExtra("c38"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c38"));
			//tva.setText("Find the largest number");
			AdBuddiz.cacheAds(this); offset="c38";
			
		}
		
		if(i.hasExtra("c39"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c39"));
			//tva.setText("Find the smallest number");
			AdBuddiz.cacheAds(this); offset="c39";
			
		}
		
		if(i.hasExtra("c40"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c40"));
			//tva.setText("Sort data in ascending order");
			AdBuddiz.cacheAds(this); offset="c40";
			
		}
		
		if(i.hasExtra("c41"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c41"));
			//tva.setText("Sort data in descending order");
			AdBuddiz.cacheAds(this); offset="c41";
			
		}
		
		if(i.hasExtra("c42"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c42"));
			///tva.setText("Add 2 bytes");
			AdBuddiz.cacheAds(this); offset="c42";
			
		}
		
		if(i.hasExtra("c43"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c43"));
			//tva.setText("Add 2 words");
			AdBuddiz.cacheAds(this); offset="c43";
			
		}
		
		if(i.hasExtra("c44"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c44"));
			//tva.setText("Subtract 2 bytes");
			AdBuddiz.cacheAds(this); offset="c44";
			
		}
		
		if(i.hasExtra("c45"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c45"));
			//tva.setText("Subtract 2 words");
			AdBuddiz.cacheAds(this); offset="c45";
			
		}
		
		if(i.hasExtra("c46"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c46"));
			//tva.setText("Multiply 2 bytes");
			AdBuddiz.cacheAds(this); offset="c46";
			
		}
		
		if(i.hasExtra("c47"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c47"));
			//tva.setText("Multiply byte with word");
			AdBuddiz.cacheAds(this); offset="c47";
			
		}
		
		if(i.hasExtra("c48"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c48"));
			//tva.setText("Multiply 2 words");
			AdBuddiz.cacheAds(this); offset="c48";
			
		}
		
		if(i.hasExtra("c49"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c49"));
			//tva.setText("Square a number");
			AdBuddiz.cacheAds(this); offset="c49";
			
		}
		
		if(i.hasExtra("c50"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c50"));
			//tva.setText("Cube a number");
			AdBuddiz.cacheAds(this); offset="c50";
			
		}
		
		if(i.hasExtra("c51"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c51"));
			//tva.setText("Division of 2 bytes");
			AdBuddiz.cacheAds(this); offset="c51";
			
		}
		
		if(i.hasExtra("c52"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c52"));
			//tva.setText("Check for oddness");
			AdBuddiz.cacheAds(this); offset="c52";
			
		}
		
		if(i.hasExtra("c53"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c53"));
			//tva.setText("Check for positiveness");
			AdBuddiz.cacheAds(this); offset="c53";
			
		}
		
		if(i.hasExtra("c54"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c54"));
			//tva.setText("Count 1's and 0's in a number");
			AdBuddiz.cacheAds(this); offset="c54";
			
		}
		
		if(i.hasExtra("c55"))
		{
			 
			  
			sendprogram = i.getStringExtra(("c55"));
			//tva.setText("Generate Fibonacci Series");
			AdBuddiz.cacheAds(this); offset="c55";
			
		}
		
		if(i.hasExtra("c56"))
		{
			 
			  
			sendprogram = i.getStringExtra("c56");
			//tva.setText("ASCII to unpacked BCD");
			AdBuddiz.cacheAds(this); offset="c56";
			
		}
		
		if(i.hasExtra("c57"))
		{
			 
			  
			sendprogram = i.getStringExtra("c57");
			//tva.setText("Unpacked BCD to ASCII");
			AdBuddiz.cacheAds(this); offset="c57";
			
		}
		
		if(i.hasExtra("c58"))
		{
			 
			  
			sendprogram = i.getStringExtra("c58");
			//tva.setText("Packed BCD to ASCII");
			AdBuddiz.cacheAds(this); offset="c58";
			
		}
		
		if(i.hasExtra("c59"))
		{
			 
			  
			sendprogram = i.getStringExtra("c59");
			//tva.setText("ASCII to packed BCD");
			AdBuddiz.cacheAds(this); offset="c59";
			
		}
		
		if(i.hasExtra("c60"))
		{
			 
			  
			sendprogram = i.getStringExtra("c60");
			//tva.setText("Hexadecimal to Decimal");
			AdBuddiz.cacheAds(this); offset="c60";
			
		}
		
		if(i.hasExtra("c61"))
		{
			 
			  
			sendprogram = i.getStringExtra("c61");
			//tva.setText("Decimal to Hexadecimal");
			AdBuddiz.cacheAds(this); offset="c61";
			
		}
		
		if(i.hasExtra("c62"))
		{
			 
			  
			sendprogram = i.getStringExtra("c62");
			//tva.setText("HEX up/down count");
			AdBuddiz.cacheAds(this); offset="c62";
			
		}
		
		if(i.hasExtra("c63"))
		{
			 
			  
			sendprogram = i.getStringExtra("c63");
			//tva.setText("BCD up count");
			AdBuddiz.cacheAds(this); offset="c63";
			
		}
		
		if(i.hasExtra("c64"))
		{
			 
			  
			sendprogram = i.getStringExtra("c64");
			//tva.setText("BCD down count");
			AdBuddiz.cacheAds(this); offset="c64";
			
		}
		
		if(i.hasExtra("c65"))
		{
			 
			  
			sendprogram = i.getStringExtra("c65");
			//tva.setText("GCD and LCM of 2 numbers");
			AdBuddiz.cacheAds(this); offset="c65";
			
		}
		
		if(i.hasExtra("c66"))
		{
			 
			  
			sendprogram = i.getStringExtra("c66");
			//tva.setText("Factorial of a number");
			AdBuddiz.cacheAds(this); offset="c66";
			
		}
		
		if(i.hasExtra("c67"))
		{
			 
			  
			sendprogram = i.getStringExtra("c67");
			//tva.setText("Generate square wave - 1");
			AdBuddiz.cacheAds(this); offset="c67";
			
		}
		
		if(i.hasExtra("c68"))
		{
			 
			  
			sendprogram = i.getStringExtra("c68");
			//tva.setText("Generate square wave - 2");
			AdBuddiz.cacheAds(this); offset="c68";
			
		}
		
		if(i.hasExtra("c69"))
		{
			 
			  
			sendprogram = i.getStringExtra("c69");
			//tva.setText("Serially send a byte");
			AdBuddiz.cacheAds(this); offset="c69";
			
		}
		
		if(i.hasExtra("c70"))
		{
			 
			  
			sendprogram = i.getStringExtra("c70");
			//tva.setText("Realize a Boolean Expression");
			AdBuddiz.cacheAds(this); offset="c70";
			
		}
		
		
		if(i.hasExtra("c71"))
		{
			 
			  
			sendprogram = i.getStringExtra("c71");
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c71";
			
		}
		
		if(i.hasExtra("c72"))
		{
			 
			  
			sendprogram = i.getStringExtra("c72");
			//tva.setText("Transfer block of data");
			AdBuddiz.cacheAds(this); offset="c72";
			
		}
		
		if(i.hasExtra("c73"))
		{
			 
			  
			sendprogram = i.getStringExtra("c73");
			//tva.setText("Find the largest number");
			AdBuddiz.cacheAds(this); offset="c73";
			
		}
		
		if(i.hasExtra("c74"))
		{
			 
			  
			sendprogram = i.getStringExtra("c74");
			//tva.setText("Find the smallest number");
			AdBuddiz.cacheAds(this); offset="c74";
			
		}
		
		if(i.hasExtra("c75"))
		{
			 
			  
			sendprogram = i.getStringExtra("c75");
			//tva.setText("Sort data in ascending order");
			AdBuddiz.cacheAds(this); offset="c75";
			
		}
		
		if(i.hasExtra("c76"))
		{
			 
			  
			sendprogram = i.getStringExtra("c76");
			//tva.setText("Sort data in descending order");
			AdBuddiz.cacheAds(this); offset="c76";
			
		}
		
		if(i.hasExtra("c77"))
		{
			 
			  
			sendprogram = i.getStringExtra("c77");
			//tva.setText("Add 2 bytes");
			AdBuddiz.cacheAds(this); offset="c77";
			
		}
		
		if(i.hasExtra("c78"))
		{
			 
			  
			sendprogram = i.getStringExtra("c78");
			//tva.setText("Add 2 words");
			AdBuddiz.cacheAds(this); offset="c78";
			
		}
		
		if(i.hasExtra("c79"))
		{
			 
			  
			sendprogram = i.getStringExtra("c79");
			//tva.setText("Subtract 2 bytes");
			AdBuddiz.cacheAds(this); offset="c79";
			
		}
		
		if(i.hasExtra("c80"))
		{
			 
			  
			sendprogram = i.getStringExtra("c80");
			//tva.setText("Subtract 2 words");
			AdBuddiz.cacheAds(this); offset="c80";
			
		}
		
		if(i.hasExtra("c81"))
		{
			 
			  
			sendprogram = i.getStringExtra("c81");
			//tva.setText("Multiply 2 bytes");
			AdBuddiz.cacheAds(this); offset="c81";
			
		}
		
		if(i.hasExtra("c82"))
		{
			 
			  
			sendprogram = i.getStringExtra("c82");
			//tva.setText("Multiply byte with word");
			AdBuddiz.cacheAds(this); offset="c82";
			
		}
		
		if(i.hasExtra("c83"))
		{
			 
			  
			sendprogram = i.getStringExtra("c83");
			//tva.setText("Multiply 2 words");
			AdBuddiz.cacheAds(this); offset="c83";
			
		}
		
		if(i.hasExtra("c84"))
		{
			 
			  
			sendprogram = i.getStringExtra("c84");
			//tva.setText("Square a number");
			AdBuddiz.cacheAds(this); offset="c84";
			
		}
		
		if(i.hasExtra("c85"))
		{
			 
			  
			sendprogram = i.getStringExtra("c85");
			//tva.setText("Cube a number");
			AdBuddiz.cacheAds(this); offset="c85";
			
		}
		
		if(i.hasExtra("c86"))
		{
			 
			  
			sendprogram = i.getStringExtra("c86");
			//tva.setText("Division of 2 bytes");
			AdBuddiz.cacheAds(this); offset="c86";
			
		}
		
		if(i.hasExtra("c87"))
		{
			 
			  
			sendprogram = i.getStringExtra("c87");
			//tva.setText("Check for oddness");
			AdBuddiz.cacheAds(this); offset="c87";
			
		}
		
		if(i.hasExtra("c88"))
		{
			 
			  
			sendprogram = i.getStringExtra("c88");
			//tva.setText("Check for positiveness");
			AdBuddiz.cacheAds(this); offset="c88";
			
		}

		AdBuddiz.showAd(this);
	}

	public void showcode(View v)
	{
		b1 = (Button)findViewById(R.id.button3);
		Intent j = new Intent(Disclaimer.this,Programs.class);
		j.putExtra(offset, sendprogram);
		startActivity(j);
		finish();
	}


	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		AdBuddiz.showAd(this);
		finish();

	}
}
