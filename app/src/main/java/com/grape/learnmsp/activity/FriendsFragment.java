package com.grape.learnmsp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grape.learnmsp.R;

/**
 * Created by Ravi on 29/07/15.
 */
public class FriendsFragment extends Fragment implements View.OnClickListener {

    Button b[] = new Button[88];
    int buttonId[] = {R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11,
            R.id.button12,
            R.id.button13,
            R.id.button14,
            R.id.button15,
            R.id.button16,
            R.id.button17,
            R.id.button18,
            R.id.button19,
            R.id.button20,
            R.id.button21,
            R.id.button22,
            R.id.button23,
            R.id.button24,
            R.id.button25,
            R.id.button26,
            R.id.button27,
            R.id.button28,
            R.id.button29,
            R.id.button30,
            R.id.button31,
            R.id.button32,
            R.id.button33,
            R.id.button34,
            R.id.button35,
            R.id.button36,
            R.id.button37,
            R.id.button38,
            R.id.button39,
            R.id.button40,
            R.id.button41,
            R.id.button42,
            R.id.button43,
            R.id.button44,
            R.id.button45,
            R.id.button46,
            R.id.button47,
            R.id.button48,
            R.id.button49,
            R.id.button50,
            R.id.button51,
            R.id.button52,
            R.id.button53,
            R.id.button54,
            R.id.button55,
            R.id.button56,
            R.id.button57,
            R.id.button58,
            R.id.button59,
            R.id.button60,
            R.id.button61,
            R.id.button62,
            R.id.button63,
            R.id.button64,
            R.id.button65,
            R.id.button66,
            R.id.button67,
            R.id.button68,
            R.id.button69,
            R.id.button70,
            R.id.button71,
            R.id.button72,
            R.id.button73,
            R.id.button74,
            R.id.button75,
            R.id.button76,
            R.id.button77,
            R.id.button78,
            R.id.button79,
            R.id.button80,
            R.id.button81,
            R.id.button82,
            R.id.button83,
            R.id.button84,
            R.id.button85,
            R.id.button86,
            R.id.button87,
            R.id.button88
    };

        static String program1 = "//  MSP430F543xA Demo - Software Toggle P1.0\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO\n"+
                "//   Built with CCS Version: 5.2.1 and IAR Embedded Workbench Version: 5.51.1\n"+
                "//******************************************************************************\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+
                " P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+

                "  while (1)                                 // Test P1.4\n"+
                "  {\n"+
                "      P1OUT ^= 0x01;                        // Toggle P1.0 using exclusive-OR\n"+
                "      __delay_cycles(100000);\n"+
                "  }\n"+
                "}\n";


        static String program2 = "//   MSP430F543xA Demo - ADC12_A, Sample A0, Set P1.0 if A0 > 0.5*AVcc\n"+
                "//\n"+
                "//   Description: A single sample is made on A0 with reference to AVcc.\n"+
                "//   Software sets ADC12SC to start sample and conversion - ADC12SC\n"+
                "//   automatically cleared at EOC. ADC12 internal oscillator times sample (16x)\n"+
                "//   and conversion. In mainloop  waits in LPM0 to save power until ADC12\n"+
                "//   conversion complete, ADC12_ISR will force exit from LPM0 in Mainloop on\n"+
                "//   reti. If A0 > 0.5*AVcc, P1.0 set, else reset.\n"+


                "#include <msp430.h>\n"+
                "int temp;\n"+
                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                " ADC12CTL0 = ADC12SHT02 + ADC12ON;         // Sampling time, ADC12 on\n"+
                "  ADC12CTL1 = ADC12SHP;                     // Use sampling timer\n"+
                " ADC12IE = 0x01;                           // Enable interrupt\n"+
                "  ADC12CTL0 |= ADC12ENC;\n"+

                " P6SEL |= 0x01;                            // P6.0 ADC option select\n"+
                "  P1DIR |= BIT0;                            // P1.0 output\n"+

                "  __delay_cycles(10000); \n"+

                "  while (1)\n"+
                "  {\n"+
                "    ADC12CTL0 |= ADC12SC;                   // Start sampling/conversion\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // LPM0, ADC12_ISR will force exit\n"+
                "    __no_operation();                       // For debugger\n"+
                "  }\n"+
                "}\n"+

                "#pragma vector = ADC12_VECTOR\n"+
                "__interrupt void ADC12_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(ADC12IV,34))\n"+
                " {\n"+
                "  case  0: break;                           // Vector  0:  No interrupt\n"+
                "  case  2: break;                           // Vector  2:  ADC overflow\n"+
                "  case  4: break;                           // Vector  4:  ADC timing overflow\n"+
                "  case  6:                                  // Vector  6:  ADC12IFG0\n"+
                "  temp = ADC12MEM0;\n"+
                "   if (ADC12MEM0 >= 0x7ff)                 // ADC12MEM = A0 > 0.5AVcc?\n"+
                "     P1OUT |= BIT0;                        // P1.0 = 1\n"+
                "    else\n"+
                "      P1OUT &= ~BIT0;                       // P1.0 = 0\n"+

                "    __bic_SR_register_on_exit(LPM0_bits);   // Exit active CPU\n"+
                "    break;\n"+
                "  case  8: break;                           // Vector  8:  ADC12IFG1\n"+
                "  case 10: break;                           // Vector 10:  ADC12IFG2\n"+
                "  case 12: break;                           // Vector 12:  ADC12IFG3\n"+
                "  case 14: break;                           // Vector 14:  ADC12IFG4\n"+
                "  case 16: break;                           // Vector 16:  ADC12IFG5\n"+
                "  case 18: break;                           // Vector 18:  ADC12IFG6\n"+
                "  case 20: break;                           // Vector 20:  ADC12IFG7\n"+
                "  case 22: break;                           // Vector 22:  ADC12IFG8\n"+
                "  case 24: break;                           // Vector 24:  ADC12IFG9\n"+
                "  case 26: break;                           // Vector 26:  ADC12IFG10\n"+
                "  case 28: break;                           // Vector 28:  ADC12IFG11\n"+
                "  case 30: break;                           // Vector 30:  ADC12IFG12\n"+
                "  case 32: break;                           // Vector 32:  ADC12IFG13\n"+
                "  case 34: break;                           // Vector 34:  ADC12IFG14\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";


        static String program3= "//  MSP430F543xA Demo - ADC12_A, Using the Internal Reference\n"+
                "//\n"+
                "//  Description: This example shows how to use the shared reference for ADC12\n"+
                "//  sampling and performs a single conversion on channel A0. The conversion \n"+
                "//  results are stored in ADC12MEM0. Test by applying a voltage to channel A0, \n"+
                "//  then setting and running to a break point at the '__no_operation()' \n"+
                "//  instruction. To view the conversion results, open an ADC12 register window \n"+
                "//  in debugger and view the contents of ADC12MEM0\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P6SEL |= BIT0;                            // Enable A/D channel A0\n"+

                "  /* Initialize REF module */\n"+
                "  // Enable 2.5V shared reference, disable temperature sensor to save power\n"+
                "  REFCTL0 |= REFMSTR+REFVSEL_2+REFON+REFTCOFF; \n"+

                "  /* Initialize ADC12 */  \n"+
                "  ADC12CTL0 = ADC12ON+ADC12SHT02;           // Turn on ADC12, set sampling time\n"+
                "  ADC12CTL1 = ADC12SHP;                     // Use sampling timer\n"+
                "  ADC12MCTL0 = ADC12SREF_1;                 // Vr+=Vref+ and Vr-=AVss\n"+

                "  __delay_cycles(75);                       // 75 us delay @ ~1MHz\n"+

                "  ADC12CTL0 |= ADC12ENC;                    // Enable conversions\n"+

                "  while (1)\n"+
                "  {\n"+
                "   ADC12CTL0 |= ADC12SC;                   // Start conversion\n"+
                "    while (!(ADC12IFG & BIT0));\n"+
                "    __no_operation();                       // SET BREAKPOINT HERE\n"+
                "  }\n"+
                "}\n";


        static String program4 = "//  MSP430F543xA Demo - ADC12_A, Using an External Reference\n"+
                "//\n"+
                "//  Description: This example shows how to use an external positive reference for\n"+
                "//  the ADC12.The external reference is applied to the VeREF+ pin. AVss is used\n"+
                "//  for the negative reference. A single conversion is performed on channel A0.\n"+
                "//  The conversion results are stored in ADC12MEM0 and Test by applying a voltage\n"+
                "//  to channel A0, then setting and running to a break point at the '_NOP()'\n"+
                "//  instruction. To view the conversion results, open the register window in \n"+
                "//  the debugger and view the contents of ADC12MEM0.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P6SEL |= BIT0;                     		// Enable VeREF+ & A/D channel A0 \n"+

                " /* Initialize ADC12 */\n"+
                " ADC12CTL0 = ADC12ON+ADC12SHT0_2;          // Turn on ADC12, set sampling time\n"+
                "  ADC12CTL1 = ADC12SHP;                     // Use sampling timer\n"+
                "  ADC12MCTL0 = ADC12SREF_2;                 // Vr+ = VeREF+ (ext) and Vr-=AVss\n"+
                "  ADC12CTL0 |= ADC12ENC;                    // Enable conversions\n"+

                "  while (1)\n"+
                "  {\n"+
                "    ADC12CTL0 |= ADC12SC;                   // Start conversion-software trigger\n"+
                "    while (!(ADC12IFG & BIT0));\n"+
                "    __no_operation();                       // SET BREAKPOINT HERE\n"+
                "  }\n"+
                "}\n";


        static String program5 = "//  MSP430F543xA Demo - ADC12_A, Repeated Sequence of Conversions\n"+
                "//\n"+
                "//  Description: This example shows how to perform a repeated sequence of\n"+
                "//  conversions using 'repeat sequence-of-channels' mode.  AVcc is used for the\n"+
                "//  reference and repeated sequence of conversions is performed on Channels A0,\n"+
                "//  A1, A2, and A3. Each conversion result is stored in ADC12MEM0, ADC12MEM1,\n"+
                "//  ADC12MEM2, and ADC12MEM3 respectively. After each sequence, the 4 conversion\n"+
                "//  results are moved to A0results[], A1results[], A2results[], and A3results[].\n"+
                "//  Test by applying voltages to channels A0 - A3. Open a watch window in\n"+
                "//  debugger and view the results. Set Breakpoint1 in the index increment line\n"+
                "//  to see the array values change sequentially and Breakpoint2 to see the entire\n"+
                "//  array of conversion results in A0results[], A1results[], A2results[], and\n"+
                "//  A3results[]for the specified Num_of_Results.\n"+
                "//\n"+
                "//  Note that a sequence has no restrictions on which channels are converted.\n"+
                "//  For example, a valid sequence could be A0, A3, A2, A4, A2, A1, A0, and A7.\n"+
                "//  See the 5xx User's Guide for instructions on using the ADC12_A.\n"+


                "#include <msp430.h>\n"+

                "#define   Num_of_Results   8\n"+

                "volatile unsigned int A0results[Num_of_Results];\n"+
                "volatile unsigned int A1results[Num_of_Results];\n"+
                "volatile unsigned int A2results[Num_of_Results];\n"+
                "volatile unsigned int A3results[Num_of_Results];\n"+

                "int main(void)\n"+
                "{\n"+
                " WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P6SEL = 0x0F;                             // Enable A/D channel inputs\n"+

                "  ADC12CTL0 = ADC12ON+ADC12MSC+ADC12SHT0_8; // Turn on ADC12_A, extend sampling time\n"+
                " // to avoid overflow of results\n"+
                "  ADC12CTL1 = ADC12SHP+ADC12CONSEQ_3;       // Use sampling timer, repeated sequence\n"+
                "  ADC12MCTL0 = ADC12INCH_0;                 // ref+=AVcc, channel = A0\n"+
                "  ADC12MCTL1 = ADC12INCH_1;                 // ref+=AVcc, channel = A1\n"+
                "  ADC12MCTL2 = ADC12INCH_2;                 // ref+=AVcc, channel = A2\n"+
                "  ADC12MCTL3 = ADC12INCH_3+ADC12EOS;        // ref+=AVcc, channel = A3, end seq.\n"+
                "  ADC12IE = 0x08;                           // Enable ADC12IFG.3\n"+
                "  ADC12CTL0 |= ADC12ENC;                    // Enable conversions\n"+
                "  ADC12CTL0 |= ADC12SC;                     // Start conversion - software trigger\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, Enable interrupts\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n"+

                "#pragma vector=ADC12_VECTOR\n"+
                "__interrupt void ADC12ISR (void)\n"+
                "{\n"+
                " static unsigned int index = 0;\n"+

                "  switch(__even_in_range(ADC12IV,34))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0:  No interrupt\n"+
                "  case  2: break;                           // Vector  2:  ADC overflow\n"+
                "  case  4: break;                           // Vector  4:  ADC timing overflow\n"+
                "  case  6: break;                           // Vector  6:  ADC12IFG0\n"+
                "  case  8: break;                           // Vector  8:  ADC12IFG1\n"+
                "  case 10: break;                           // Vector 10:  ADC12IFG2\n"+
                "  case 12:                                  // Vector 12:  ADC12IFG3\n"+
                "    A0results[index] = ADC12MEM0;           // Move A0 results, IFG is cleared\n"+
                "    A1results[index] = ADC12MEM1;           // Move A1 results, IFG is cleared\n"+
                "    A2results[index] = ADC12MEM2;           // Move A2 results, IFG is cleared\n"+
                "    A3results[index] = ADC12MEM3;           // Move A3 results, IFG is cleared\n"+
                "    index++;                                // Increment results index, modulo; Set Breakpoint1 here\n"+

                "    if (index == 8) \n"+
                "    	index = 0;							// Reset index, Set breakpoint 2 here\n"+

                "  case 14: break;                           // Vector 14:  ADC12IFG4\n"+
                "  case 16: break;                           // Vector 16:  ADC12IFG5\n"+
                "  case 18: break;                           // Vector 18:  ADC12IFG6\n"+
                "  case 20: break;                           // Vector 20:  ADC12IFG7\n"+
                "  case 22: break;                           // Vector 22:  ADC12IFG8\n"+
                "  case 24: break;                           // Vector 24:  ADC12IFG9\n"+
                "  case 26: break;                           // Vector 26:  ADC12IFG10\n"+
                "  case 28: break;                           // Vector 28:  ADC12IFG11\n"+
                "  case 30: break;                           // Vector 30:  ADC12IFG12\n"+
                "  case 32: break;                           // Vector 32:  ADC12IFG13\n"+
                "  case 34: break;                           // Vector 34:  ADC12IFG14\n"+
                "  default: break; \n"+
                "  } \n"+
                "}\n";

        static String program6 = "//  MSP430F543xA Demo - ADC12_A, Repeated Single Channel Conversions\n"+
                "//\n"+
                "//  Description: This example shows how to perform repeated conversions on a\n"+
                "//  single channel using \"repeat-single-channel \" mode.  AVcc is used for the\n"+
                "//  reference and repeated conversions are performed on Channel A0. Each\n"+
                "//  conversion result is moved to an 8-element array called results[].  Test by\n"+
                "//  applying a voltage to channel A0, then running. Open a watch window in\n"+
                "//  debugger and view the results. Set Breakpoint1 in the index increment line\n"+
                "//  to see the array value change sequentially and Breakpoint to see the entire\n"+
                "//  array of conversion results in \"results[] \" for the specified Num_of_Results.\n"+
                "//  This can run even in LPM4 mode as ADC has its own clock (MODOSC)\n"+


                "#include <msp430.h>\n"+

                "#define   Num_of_Results   8\n"+

                "volatile unsigned int results[Num_of_Results];\n"+
                "// Needs to be global in this example. Otherwise, the compiler removes it because it\n"+
                "//is not used for anything.\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P6SEL |= 0x01;                            // Enable A/D channel A0\n"+

                "  /* Initialize ADC12_A */ \n"+
                "  ADC12CTL0 = ADC12ON+ADC12SHT0_8+ADC12MSC; // Turn on ADC12, set sampling time\n"+
                "                                           // set multiple sample conversion\n"+
                "  ADC12CTL1 = ADC12SHP+ADC12CONSEQ_2;       // Use sampling timer, set mode\n"+
                "  ADC12IE = 0x01;                           // Enable ADC12IFG.0\n"+
                "  ADC12CTL0 |= ADC12ENC;                    // Enable conversions\n"+
                "  ADC12CTL0 |= ADC12SC;                     // Start conversion\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4, Enable interrupts\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n"+

                "#pragma vector=ADC12_VECTOR\n"+
                "__interrupt void ADC12ISR (void)\n"+
                "{\n"+
                " static unsigned char index = 0;\n"+

                "  switch(__even_in_range(ADC12IV,34))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0:  No interrupt\n"+
                "  case  2: break;                           // Vector  2:  ADC overflow\n"+
                "  case  4: break;                           // Vector  4:  ADC timing overflow\n"+
                "  case  6:                                  // Vector  6:  ADC12IFG0\n"+
                "    results[index] = ADC12MEM0;             // Move results\n"+
                "    index++;                                // Increment results index, modulo; Set Breakpoint1 here\n"+

                "   if (index == 8)\n"+
                "      index = 0;                            // Reset the index; Set Breakpoint here \n"+

                "  case  8: break;                           // Vector  8:  ADC12IFG1\n"+
                "  case 10: break;                           // Vector 10:  ADC12IFG2\n"+
                "  case 12: break;                           // Vector 12:  ADC12IFG3\n"+
                "  case 14: break;                           // Vector 14:  ADC12IFG4\n"+
                "  case 16: break;                           // Vector 16:  ADC12IFG5\n"+
                "  case 18: break;                           // Vector 18:  ADC12IFG6\n"+
                "  case 20: break;                           // Vector 20:  ADC12IFG7\n"+
                "  case 22: break;                           // Vector 22:  ADC12IFG8\n"+
                "  case 24: break;                           // Vector 24:  ADC12IFG9\n"+
                "  case 26: break;                           // Vector 26:  ADC12IFG10\n"+
                "  case 28: break;                           // Vector 28:  ADC12IFG11\n"+
                "  case 30: break;                           // Vector 30:  ADC12IFG12\n"+
                "  case 32: break;                           // Vector 32:  ADC12IFG13\n"+
                "  case 34: break;                           // Vector 34:  ADC12IFG14\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";

        static String program7 = "//  MSP430F543xA Demo - ADC12_A, Sequence of Conversions (non-repeated)\n"+
                "//\n"+
                "//  Description: This example shows how to perform A/D conversions on a sequence\n"+
                "//  of channels. A single sequence of conversions is performed - one conversion\n"+
                "//  each on channels A0, A1, A2, and A3. Each conversion uses AVcc and AVss for\n"+
                "//  the references. The conversion results are stored in ADC12MEM0, ADC12MEM1,\n"+
                "//  ADC12MEM2, and ADC12MEM3 respectively and are moved to 'results[]' upon\n"+
                "//  completion of the sequence. Test by applying voltages to pins A0, A1, A2,\n"+
                "//  and A3, then setting and running to a break point at the \"_BIC...\" \n"+
                "//  instruction in the ISR. To view the conversion results, open a watch window\n"+
                "//  in debugger and view 'results' or view ADC12MEM0, ADC12MEM1, ADC12MEM2, and\n"+
                "//  ADC12MEM3 in an ADC12 SFR window.\n"+
                "//  This can run even in LPM4 mode as ADC has its own clock\n"+
                "//  Note that a sequence has no restrictions on which channels are converted.\n"+
                "//  For example, a valid sequence could be A0, A3, A2, A4, A2, A1, A0, and A7.\n"+
                "//  See the 5xx User's Guide for instructions on using the ADC12.\n"+


                "#include <msp430.h>\n"+

                "volatile unsigned int results[4];           // Needs to be global in this example\n"+
                "                                            // Otherwise, the compiler removes it\n"+
                "                                            // because it is not used for anything.\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P6SEL = 0x0F;                             // Enable A/D channel inputs\n"+

                "  /* Initialize ADC12_A */ \n"+
                "  ADC12CTL0 = ADC12ON+ADC12MSC+ADC12SHT0_2; // Turn on ADC12, set sampling time\n"+
                "  ADC12CTL1 = ADC12SHP+ADC12CONSEQ_1;       // Use sampling timer, single sequence\n"+
                "  ADC12MCTL0 = ADC12INCH_0;                 // ref+=AVcc, channel = A0\n"+
                "  ADC12MCTL1 = ADC12INCH_1;                 // ref+=AVcc, channel = A1\n"+
                "  ADC12MCTL2 = ADC12INCH_2;                 // ref+=AVcc, channel = A2\n"+
                "  ADC12MCTL3 = ADC12INCH_3+ADC12EOS;        // ref+=AVcc, channel = A3, end seq.\n"+
                "  ADC12IE = 0x08;                           // Enable ADC12IFG.3\n"+
                "  ADC12CTL0 |= ADC12ENC;                    // Enable conversions\n"+

                " while(1)\n"+
                "  {\n"+
                "    ADC12CTL0 |= ADC12SC;                   // Start convn - software trigger\n"+

                "    __bis_SR_register(LPM4_bits + GIE);     // Enter LPM4, Enable interrupts\n"+
                "    __no_operation();                       // For debugger  \n"+
                "  }\n"+
                "}\n"+

                "#pragma vector=ADC12_VECTOR\n"+
                "__interrupt void ADC12ISR (void)\n"+
                "{\n"+
                "  switch(__even_in_range(ADC12IV,34))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0:  No interrupt\n"+
                "  case  2: break;                           // Vector  2:  ADC overflow\n"+
                "  case  4: break;                           // Vector  4:  ADC timing overflow\n"+
                "  case  6: break;                           // Vector  6:  ADC12IFG0\n"+
                "  case  8: break;                           // Vector  8:  ADC12IFG1\n"+
                "  case 10: break;                           // Vector 10:  ADC12IFG2\n"+
                "  case 12:                                  // Vector 12:  ADC12IFG3\n"+
                "    results[0] = ADC12MEM0;                 // Move results, IFG is cleared\n"+
                "    results[1] = ADC12MEM1;                 // Move results, IFG is cleared\n"+
                "    results[2] = ADC12MEM2;                 // Move results, IFG is cleared\n"+
                "    results[3] = ADC12MEM3;                 // Move results, IFG is cleared\n"+
                "    __bic_SR_register_on_exit(LPM4_bits);   // Exit active CPU, SET BREAKPOINT HERE  \n"+
                "  case 14: break;                           // Vector 14:  ADC12IFG4\n"+
                "  case 16: break;                           // Vector 16:  ADC12IFG5\n"+
                "  case 18: break;                           // Vector 18:  ADC12IFG6\n"+
                "  case 20: break;                           // Vector 20:  ADC12IFG7\n"+
                "  case 22: break;                           // Vector 22:  ADC12IFG8\n"+
                "  case 24: break;                           // Vector 24:  ADC12IFG9\n"+
                "  case 26: break;                           // Vector 26:  ADC12IFG10\n"+
                "  case 28: break;                           // Vector 28:  ADC12IFG11\n"+
                "  case 30: break;                           // Vector 30:  ADC12IFG12\n"+
                "  case 32: break;                           // Vector 32:  ADC12IFG13\n"+
                "  case 34: break;                           // Vector 34:  ADC12IFG14\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";


        static String program8 = "//  MSP430F543xA Demo - ADC12_A, Sample A10 Temp and Convert to oC and oF\n"+
                "//\n"+
                "//  Description: A single sample is made on A10 with reference to internal\n"+
                "//  1.5V Vref. Software sets ADC12SC to start sample and conversion - ADC12SC\n"+
                "//  automatically cleared at EOC. ADC12 internal oscillator times sample\n"+
                "//  and conversion. In Mainloop MSP430 waits in LPM4 to save power until\n"+
                "//  ADC10 conversion complete, ADC12_ISR will force exit from any LPMx in\n"+
                "//  Mainloop on reti.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO ~ 1.045MHz, ADC12CLK = ADC12OSC\n"+
                "//\n"+
                "//  Uncalibrated temperature measured from device to devive will vary due to\n"+
                "//  slope and offset variance from device to device - please see datasheet.\n"+
                "//  Note:Use the TLV calibrated temperature to measure temperature \n"+
                "// (the TLV CALIBRATED DATA IS STORED IN THE INFORMATION SEGMENT, SEE DEVICE DATASHEET)\n"+

                "#include <msp430.h>\n"+

                "#define CALADC12_15V_30C  *((unsigned int *)0x1A1A)   // Temperature Sensor Calibration-30 C\n"+
                //See device datasheet for TLV table memory mapping\n"+
                "#define CALADC12_15V_85C  *((unsigned int *)0x1A1C)   // Temperature Sensor Calibration-85 C\n"+

                "unsigned int temp;\n"+
                "volatile float temperatureDegC;\n"+
                "volatile float temperatureDegF;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+

                "  /* Initialize the shared reference module */ \n"+
                "  REFCTL0 |= REFMSTR + REFVSEL_0 + REFON;    // Enable internal 1.5V reference\n"+

                "  /* Initialize ADC12_A */ \n"+
                "  ADC12CTL0 = ADC12SHT0_8 + ADC12ON;		    // Set sample time \n"+
                "  ADC12CTL1 = ADC12SHP;                     // Enable sample timer\n"+
                "  ADC12MCTL0 = ADC12SREF_1 + ADC12INCH_10;  // ADC input ch A10 => temp sense \n"+
                "  ADC12IE = 0x001;                          // ADC_IFG upon conv result-ADCMEMO\n"+

                "  __delay_cycles(75);                       // delay to allow Ref to settle\n"+
                "                                            // based on default DCO frequency.\n"+
                "                                           // See Datasheet for typical settle\n"+
                "                                            // time.\n"+
                "  ADC12CTL0 |= ADC12ENC;\n"+

                "  while(1)\n"+
                "  {\n"+
                "    ADC12CTL0 &= ~ADC12SC; \n"+
                "	ADC12CTL0 |= ADC12SC;                   // Sampling and conversion start\n"+

                "    __bis_SR_register(LPM4_bits + GIE);     // LPM4 with interrupts enabled\n"+
                "    __no_operation();\n"+

                "    // Temperature in Celsius. See the Device Descriptor Table section in the\n"+
                "    // System Resets, Interrupts, and Operating Modes, System Control Module\n"+
                "    // chapter in the device user's guide for background information on the\n"+
                "    // used formula.\n"+
                "    temperatureDegC = (float)(((long)temp - CALADC12_15V_30C) * (85 - 30)) /\n"+
                "            (CALADC12_15V_85C - CALADC12_15V_30C) + 30.0f;\n"+

                "    // Temperature in Fahrenheit Tf = (9/5)*Tc + 32\n"+
                "    temperatureDegF = temperatureDegC * 9.0f / 5.0f + 32.0f;\n"+

                "    __no_operation();                       // SET BREAKPOINT HERE\n"+
                "  }\n"+
                "}\n"+

                "#pragma vector=ADC12_VECTOR\n"+
                "__interrupt void ADC12ISR (void)\n"+
                "{\n"+
                "  switch(__even_in_range(ADC12IV,34))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0:  No interrupt\n"+
                "  case  2: break;                           // Vector  2:  ADC overflow\n"+
                "  case  4: break;                           // Vector  4:  ADC timing overflow\n"+
                "  case  6:                                  // Vector  6:  ADC12IFG0\n"+
                "    temp = ADC12MEM0;                       // Move results, IFG is cleared\n"+
                "    __bic_SR_register_on_exit(LPM4_bits);   // Exit active CPU\n"+
                "    break;\n"+
                "  case  8: break;                           // Vector  8:  ADC12IFG1\n"+
                "  case 10: break;                           // Vector 10:  ADC12IFG2\n"+
                "  case 12: break;                           // Vector 12:  ADC12IFG3\n"+
                "  case 14: break;                           // Vector 14:  ADC12IFG4\n"+
                "  case 16: break;                           // Vector 16:  ADC12IFG5\n"+
                "  case 18: break;                           // Vector 18:  ADC12IFG6\n"+
                "  case 20: break;                           // Vector 20:  ADC12IFG7\n"+
                "  case 22: break;                           // Vector 22:  ADC12IFG8\n"+
                "  case 24: break;                           // Vector 24:  ADC12IFG9\n"+
                "  case 26: break;                           // Vector 26:  ADC12IFG10\n"+
                "  case 28: break;                           // Vector 28:  ADC12IFG11\n"+
                "  case 30: break;                           // Vector 30:  ADC12IFG12\n"+
                "  case 32: break;                           // Vector 32:  ADC12IFG13\n"+
                "  case 34: break;                           // Vector 34:  ADC12IFG14\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";

        static String program9 = "//  MSP430F543xA Demo - DMA0, Repeated Block to-from RAM, Software Trigger\n"+
                "//\n"+
                "//  Description: A 16 word block from 1C00-1C1Fh is transfered to 1C20h-1C3fh\n"+
                "//  using DMA0 in a burst block using software DMAREQ trigger.\n"+
                "//  After each transfer, source, destination and DMA size are\n"+
                "//  reset to inital software setting because DMA transfer mode 5 is used.\n"+
                "//  P1.0 is toggled durring DMA transfer only for demonstration purposes.\n"+
                "//  ** RAM location 0x1C00 - 0x1C3F used - make sure no compiler conflict **\n"+
                "//  ACLK = REFO = 32kHz, MCLK = SMCLK = default DCO 1048576Hz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  __data16_write_addr((unsigned short) &DMA0SA,(unsigned long) 0x1C00);\n"+
                "                                           // Source block address\n"+
                "  __data16_write_addr((unsigned short) &DMA0DA,(unsigned long) 0x1C20);\n"+
                "                                            // Destination single address\n"+
                "  DMA0SZ = 16;                              // Block size\n"+
                "  DMA0CTL = DMADT_5+DMASRCINCR_3+DMADSTINCR_3; // Rpt, inc\n"+
                "  DMA0CTL |= DMAEN;                         // Enable DMA0\n"+

                "  while(1)\n"+
                "  {\n"+
                "    P1OUT |= 0x01;                          // P1.0 = 1, LED on\n"+
                "    DMA0CTL |= DMAREQ;                      // Trigger block transfer\n"+
                "    P1OUT &= ~0x01;                         // P1.0 = 0, LED off\n"+
                "  }\n"+
                "}\n";


        static String program10= "//   MSP430F543xA Demo - DMA0, Single Transfer in Block Mode UART1 9600, ACLK\n"+
                "//\n"+
                "//   Description: DMA0 is used to transfer a string as a block to USCI_A0.\n"+
                "//   USCIA0TXIFG WILL trigger DMA0. \"Hello World\" is TX'd via 9600 baud on\n"+
                "//   USCI_A0. Watchdog in interval mode triggers block transfer every 1000ms.\n"+
                "//   Level senstive trigger used for USCIA0TXIFG to prevent loss of inital edge\n"+
                "//   sensitive triggers - USCIA0TXIFG which is set at POR.\n"+
                "//   ACLK = UCLK 32768Hz, MCLK = SMCLK = default DCO 1048576Hz\n"+
                "//   Baud rate divider with 32768hz XTAL @9600 = 32768Hz/9600 = 3.41 (0003h 4Ah)\n"+


                "#include <msp430.h>\n"+

                "const char String1[13] = \"Hello World\r\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_ADLY_1000;                   // WDT 1000ms, ACLK, interval timer\n"+
                "  SFRIE1 |= WDTIE;                          // Enable WDT interrupt\n"+
                "  P3SEL |= 0x30;                            // P3.4,5 = USCI_A0 TXD/RXD\n"+
                "  UCA0CTL1 |= UCSWRST;                      // 8-bit characters\n"+
                "  UCA0CTL1 = UCSSEL_1;                      // CLK = ACLK\n"+
                "  UCA0BR0 = 0x03;                           // 32k/9600=3.41\n"+
                "  UCA0BR1 = 0x00;\n"+
                "  UCA0MCTL = UCBRS_3+UCBRF_0;               // Modulation\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // Release USCI state machine\n"+

                "  DMACTL0 = DMA0TSEL_17;                    // USCI_A0 TXIFG trigger\n"+
                "  __data16_write_addr((unsigned short) &DMA0SA,(unsigned long) String1);\n"+
                "                                            // Source block address\n"+
                "  __data16_write_addr((unsigned short) &DMA0DA,(unsigned long) &UCA0TXBUF);\n"+
                "                                            // Destination single address\n"+
                "  DMA0SZ = sizeof(String1);                 // Block size\n"+
                "  DMA0CTL = DMASRCINCR_3+DMASBDB+DMALEVEL;  // Repeat, inc src\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 w/ interrupts\n"+
                "  __no_operation();                         // Required only for debugger\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// Trigger DMA block transfer\n"+
                "//------------------------------------------------------------------------------\n"+
                " #pragma vector=WDT_VECTOR\n"+
                "__interrupt void WDT_ISR(void)\n"+
                "{\n"+
                "  DMA0CTL |= DMAEN;                         // Enable\n"+
                "}\n";


        static String program11= "//  MSP430F543xA Demo - SPI TX & RX using DMA0 & DMA1 Single Transfer in Fixed\n"+
                "//                     Address Mode\n"+
                "//\n"+
                "//  Description: This code has to be used with MSP430F543xA_uscia0_spi_10.c as\n"+
                "//  slave SPI. DMA0 is used to transfer a single byte while DMA1 is used to\n"+
                "//  RX from slave SPI at the same time. This code will set P1.0 if RX character\n"+
                "//  is correct and clears P1.0 if received character is wrong. Watchdog in\n"+
                "//  interval mode triggers block transfer every 1000ms.\n"+
                "//  ACLK = REFO = 32kHz, MCLK = SMCLK = default DCO 1048576Hz\n"+


                "#include <msp430.h>\n"+

                "char TxString;\n"+
                "char RxString;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_ADLY_1000;                   // WDT 1000ms, ACLK, interval timer\n"+
                " SFRIE1 |= WDTIE;                           // Enable WDT interrupt\n"+
                "  P1OUT &= ~0x01;                           // Clear P1.0\n"+
                "  P1DIR |= 0x01;                            // P1.0 = Output\n"+
                "  P3SEL |= 0x31;                            // P3.0,4,5 = USCI_A0 SPI Option\n"+

                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL0 = UCMST+UCSYNC+UCCKPL+UCMSB;     // 3-pin, 8-bit SPI master\n"+
                "                                            // Clock polarity high, MSB\n"+
                "  UCA0CTL1 = UCSSEL_2;                      // SMCLK\n"+
                "  UCA0BR0 = 0x02;                           // /2\n"+
                "  UCA0BR1 = 0x00;                           //\n"+
                "  UCA0MCTL = 0x00;                          // No modulation\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+

                "  DMACTL0 = DMA1TSEL_16+DMA0TSEL_17;        // DMA0 - UCA0TXIFG\n"+
                "                                            // DMA1 - UCA0RXIFG\n"+
                "  // Setup DMA0\n"+
                "  __data16_write_addr((unsigned short) &DMA0SA,(unsigned long) &TxString);\n"+
                "                                            // Source block address\n"+
                "  __data16_write_addr((unsigned short) &DMA0DA,(unsigned long) &UCA0TXBUF);\n"+
                "                                            // Destination single address\n"+
                "  DMA0SZ = 1;                               // Block size\n"+
                "  DMA0CTL = DMASRCINCR_3+DMASBDB+DMALEVEL;  // inc src\n"+

                "  // Setup DMA1\n"+
                "  __data16_write_addr((unsigned short) &DMA1SA,(unsigned long) &UCA0RXBUF);\n"+
                "                                            // Source block address\n"+
                "  __data16_write_addr((unsigned short) &DMA1DA,(unsigned long) &RxString);\n"+
                "                                            // Destination single address\n"+
                "  DMA1SZ = 1;                               // Block size\n"+
                "  DMA1CTL = DMADSTINCR_3+DMASBDB+DMALEVEL;  // inc dst\n"+

                "  TxString = RxString = 0;                  // Clear TxString\n"+
                "                                            // Clear RxString\n"+
                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 w/ interrupts\n"+
                "  __no_operation();                         // Required only for debugger\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// Trigger DMA0 & DMA1 block transfer.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector=WDT_VECTOR\n"+
                "__interrupt void WDT_ISR(void)\n"+
                "{\n"+
                "  if(TxString-1 == RxString)\n"+
                "    P1OUT |= 0x01;                          // Set P1.0 if True\n"+
                "  else\n"+
                "    P1OUT &= ~0x01;                         // Clear P1.0 if False\n"+

                "  TxString++;                               // Increment TxString counter\n"+
                "  DMA1CTL |= DMAEN;                         // DMA1 Enable\n"+
                "  DMA0CTL |= DMAEN;                         // DMA0 Enable\n"+
                "}\n";


        static String program12=  "//  MSP430F543xA Demo - DMA0, Single transfer using ADC12 triggered by TimerB\n"+
                "//\n"+
                "//  Description: This software uses TBCCR1 as a sample and convert input into\n"+
                "//  the A0 of ADC12. ADC12IFG is used to trigger a DMA transfer and DMA\n"+
                "//  interrupt triggers when DMA transfer is done. TB1 is set as an output and\n"+
                "//  P1.0 is toggled when DMA ISR is serviced.\n"+
                "//  ACLK = REFO = 32kHz, MCLK = SMCLK = default DCO 1048576Hz\n"+


                "#include <msp430.h>\n"+

                "unsigned int DMA_DST;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Hold WDT\n"+

                "  P1OUT &= ~BIT0;                           // P1.0 clear\n"+
                "  P1DIR |= BIT0;                            // P1.0 output\n"+
                "  P4DIR |= BIT1;                            // P4.1 output\n"+
                "  P4SEL |= BIT1;                            // P4.1 select\n"+

                "  //Setup Timer B0\n"+
                "  TBCCR0 = 0xFFFE;\n"+
                "  TBCCR1 = 0x8000;\n"+
                "  TBCCTL1 = OUTMOD_3;                       // CCR1 set/reset mode\n"+
                "  TBCTL = TBSSEL_2+MC_1+TBCLR;              // SMCLK, Up-Mode\n"+

                "  // Setup ADC12\n"+
                "  ADC12CTL0 = ADC12SHT0_15+ADC12MSC+ADC12ON;// Sampling time, MSC, ADC12 on\n"+
                "  ADC12CTL1 = ADC12SHS_3+ADC12CONSEQ_2;     // Use sampling timer; ADC12MEM0\n"+
                "                                            // Sample-and-hold source = CCI0B =\n"+
                "                                            // TBCCR1 output\n"+
                "                                            // Repeated-single-channel\n"+
                "  ADC12MCTL0 = ADC12SREF_0+ADC12INCH_0;     // V+=AVcc V-=AVss, A0 channel\n"+
                "  ADC12CTL0 |= ADC12ENC;\n"+

                "  // Setup DMA0\n"+
                "  DMACTL0 = DMA0TSEL_24;                    // ADC12IFGx triggered\n"+
                "  DMACTL4 = DMARMWDIS;                      // Read-modify-write disable\n"+
                "  DMA0CTL &= ~DMAIFG;\n"+
                "  DMA0CTL = DMADT_4+DMAEN+DMADSTINCR_3+DMAIE; // Rpt single tranfer, inc dst, Int\n"+
                "  DMA0SZ = 1;                               // DMA0 size = 1\n"+
                "  __data16_write_addr((unsigned short) &DMA0SA,(unsigned long) &ADC12MEM0);\n"+
                "                                            // ... from ADC12MEM0\n"+
                "  __data16_write_addr((unsigned short) &DMA0DA,(unsigned long) &DMA_DST);\n"+
                "                                            // ... to destination in RAM\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // LPM0 w/ interrupts\n"+
                "  __no_operation();                         // used for debugging\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// DMA Interrupt Service Routine\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector=DMA_VECTOR\n"+
                "__interrupt void DMA_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(DMAIV,16))\n"+
                "  {\n"+
                "    case 0: break;\n"+
                "    case 2:                                 // DMA0IFG = DMA Channel 0\n"+
                "      P1OUT ^= BIT0;                        // Toggle P1.0\n"+
                "      break;\n"+
                "    case 4: break;                          // DMA1IFG = DMA Channel 1\n"+
                "    case 6: break;                          // DMA2IFG = DMA Channel 2\n"+
                "    case 8: break;                          // DMA3IFG = DMA Channel 3\n"+
                "    case 10: break;                         // DMA4IFG = DMA Channel 4\n"+
                "    case 12: break;                         // DMA5IFG = DMA Channel 5\n"+
                "    case 14: break;                         // DMA6IFG = DMA Channel 6\n"+
                "    case 16: break;                         // DMA7IFG = DMA Channel 7\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";



        static String program13= "//  MSP430F543xA Demo - Single-Byte Flash In-System Programming, Copy SegC to SegD\n"+
                "//\n"+
                "//  Description: This program first erases flash seg C, then it increments all\n"+
                "//  values in seg C, then it erases seg D, then copies seg C to seg D. Starting\n"+
                "//  addresses of segments defined in this code: Seg C-0x1880, Seg D-0x1800.\n"+
                "//  RESET the device to re-execute code. This is implemented to prevent\n"+
                "//  stressing of Flash unintentionally.\n"+
                "//  ACLK = REFO = 32kHz, MCLK = SMCLK = default DCO 1048576Hz\n"+


                "#include <msp430.h>\n"+

                "char value;                                 // 8-bit value to write to seg C\n"+

                "// Function prototypes\n"+
                "void write_SegC(char value);\n"+
                "void copy_C2D(void);\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  value = 0;                                // initialize value\n"+

                "  write_SegC(value);                        // Write segment C, increment value\n"+
                "  copy_C2D();                               // Copy segment C to D\n"+
                "  while(1);                                 // Loop forever, SET BREAKPOINT HERE\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// Input = value, holds value to write to Seg C\n"+
                "//------------------------------------------------------------------------------\n"+
                "void write_SegC(char value)\n"+
                "{\n"+
                "  unsigned int i;\n"+
                "  char * Flash_ptr;                         // Initialize Flash pointer\n"+
                "  Flash_ptr = (char *) 0x1880;\n"+
                "  __disable_interrupt();                    // 5xx Workaround: Disable global\n"+
                "                                            // interrupt while erasing. Re-Enable\n"+
                "                                            // GIE if needed\n"+
                "  FCTL3 = FWKEY;                            // Clear Lock bit\n"+
                "  FCTL1 = FWKEY+ERASE;                      // Set Erase bit\n"+
                "  *(unsigned int *)Flash_ptr = 0;           // Dummy write to erase Flash seg\n"+
                "  FCTL1 = FWKEY+WRT;                        // Set WRT bit for write operation\n"+

                "  for (i = 0; i < 128; i++)\n"+
                "  {\n"+
                "    *Flash_ptr++ = value++;                 // Write value to flash\n"+
                "  }\n"+
                "  FCTL1 = FWKEY;                            // Clear WRT bit\n"+
                "  FCTL3 = FWKEY+LOCK;                       // Set LOCK bit\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// Copy Seg C to Seg D\n"+
                "//------------------------------------------------------------------------------\n"+
                "void copy_C2D(void)\n"+
                "{\n"+
                "  unsigned int i;\n"+
                "  char *Flash_ptrC;\n"+
                "  char *Flash_ptrD;\n"+

                "  Flash_ptrC = (char *) 0x1880;             // Initialize Flash segment C ptr\n"+
                "  Flash_ptrD = (char *) 0x1800;             // Initialize Flash segment D ptr\n"+

                "  __disable_interrupt();                    // 5xx Workaround: Disable global\n"+
                "                                            // interrupt while erasing. Re-Enable\n"+
                "                                            // GIE if needed\n"+
                "  FCTL3 = FWKEY;                            // Clear Lock bit\n"+
                "  FCTL1 = FWKEY+ERASE;                      // Set Erase bit\n"+
                "  *(unsigned int *)Flash_ptrD = 0;          // Dummy write to erase Flash seg D\n"+
                "  FCTL1 = FWKEY+WRT;                        // Set WRT bit for write operation\n"+

                "  for (i = 0; i < 128; i++)\n"+
                "  {\n"+
                "    *Flash_ptrD++ = *Flash_ptrC++;          // copy value segment C to seg D\n"+
                "  }\n"+

                "  FCTL1 = FWKEY;                            // Clear WRT bit\n"+
                "  FCTL3 = FWKEY+LOCK;                       // Set LOCK bit\n"+
                "}\n";


        static String program14=   "//  MSP430F543xA Demo - Flash In-System Programming w/ Long-Word write at 0x1800\n"+
                "//\n"+
                "//  Description: This program first erases flash seg D, then it writes a 32-bit\n"+
                "//  value to memory location 0x1800 using long-word write mode. Long-word write\n"+
                "//  provides faster write than byte/word mode.\n"+
                "//  RESET the device to re-execute code. This is implemented to prevent\n"+
                "//  stressing of Flash unintentionally.\n"+
                "//  ACLK = REFO = 32kHz, MCLK = SMCLK = default DCO 1048576Hz\n"+
                "//  //* Set Breakpoint on NOP in the Mainloop to avoid Stressing Flash *//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  unsigned long * Flash_ptrD;               // Initialize Flash pointer Seg D\n"+
                "  unsigned long value;\n"+
                "{  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  Flash_ptrD = (unsigned long *) 0x1800;    // Initialize Flash pointer\n"+
                "  value = 0x12345678;                       // Initialize Value\n"+
                "  __disable_interrupt();                    // 5xx Workaround: Disable global\n"+
                "                                            // interrupt while erasing. Re-Enable\n"+
                "                                            // GIE if needed\n"+
                "  FCTL3 = FWKEY;                            // Clear Lock bit\n"+
                "  FCTL1 = FWKEY+ERASE;                      // Set Erase bit\n"+
                "  *Flash_ptrD = 0;                          // Dummy write to erase Flash seg\n"+
                "  FCTL1 = FWKEY+BLKWRT;                     // Enable long-word write\n"+
                "  *Flash_ptrD = value;                      // Write to Flash\n"+
                "  FCTL1 = FWKEY;                            // Clear WRT bit\n"+
                "  FCTL3 = FWKEY+LOCK;                       // Set LOCK bit\n"+
                "  while(1);                                 // Loop forever, SET BREAKPOINT HERE\n"+
                "}\n";


        static String program15=   "//   MSP430F543xA Demo - Enters LPM3 (ACLK = LFXT1)\n"+
                "//\n"+
                "//   Description: Configure ACLK = LFXT1 and enters LPM3. Measure current.\n"+
                "//   ACLK = LFXT1 = 32kHz, MCLK = SMCLK = default DCO\n"+
                "//   Note: SVS(H,L) & SVM(H,L) not disabled\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  P7SEL |= 0x03;                            // Select XT1\n"+
                "  UCSCTL6 &= ~(XT1OFF);                     // XT1 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  // Loop until XT1,XT2 & DCO stabilizes\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }\n"+
                "  while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  UCSCTL6 &= ~(XT1DRIVE_3);                 // Xtal is now stable, reduce drive\n"+
                // strength\n"+

                "  P1OUT = 0x00;\n"+
                "  P2OUT = 0x00;\n"+
                "  P3OUT = 0x00;\n"+
                "  P4OUT = 0x00;\n"+
                "  P5OUT = 0x00;\n"+
                "  P6OUT = 0x00;\n"+
                "  P7OUT = 0x00;\n"+
                "  P8OUT = 0x00;\n"+
                "  P9OUT = 0x00;\n"+
                "  P10OUT = 0x00;\n"+
                "  P11OUT = 0x00;\n"+
                "  PJOUT = 0x00;\n"+

                "  P1DIR = 0xFF;\n"+
                "  P2DIR = 0xFF;\n"+
                "  P3DIR = 0xFF;\n"+
                "  P4DIR = 0xFF;\n"+
                "  P5DIR = 0xFF;\n"+
                "  P6DIR = 0xFF;\n"+
                "  P7DIR = 0xFF;\n"+
                "  P8DIR = 0xFF;\n"+
                "  P9DIR = 0xFF;\n"+
                "  P10DIR = 0xFF;\n"+
                "  P11DIR = 0xFF;\n"+
                "  PJDIR = 0xFF;\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program16=   "//   MSP430F543xA Demo - Enters LPM3 (ACLK = VLO)\n"+
                "//\n"+
                "//   Description: Enters LPM3 with ACLK = VLO.\n"+
                "//   ACLK = MCLK = SMCLK = VLO = 12kHz\n"+
                "//   Note: SVS(H,L) & SVM(H,L) not disabled\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Hold WDT\n"+
                "  UCSCTL4 = SELM_1 + SELS_1 + SELA_1;       // MCLK = SMCLK = ACLK = VLO\n"+

                "  P1OUT = 0x00;\n"+
                "  P2OUT = 0x00;\n"+
                "  P3OUT = 0x00;\n"+
                "  P4OUT = 0x00;\n"+
                "  P5OUT = 0x00;\n"+
                "  P6OUT = 0x00;\n"+
                "  P7OUT = 0x00;\n"+
                "  P8OUT = 0x00;\n"+
                "  P9OUT = 0x00;\n"+
                "  P10OUT = 0x00;\n"+
                "  P11OUT = 0x00;\n"+
                "  PJOUT = 0x00;\n"+

                "  P1DIR = 0xFF;\n"+
                "  P2DIR = 0xFF;\n"+
                "  P3DIR = 0xFF;\n"+
                "  P4DIR = 0xFF;\n"+
                "  P5DIR = 0xFF;\n"+
                "  P6DIR = 0xFF;\n"+
                "  P7DIR = 0xFF;\n"+
                "  P8DIR = 0xFF;\n"+
                "  P9DIR = 0xFF;\n"+
                "  P10DIR = 0xFF;\n"+
                "  P11DIR = 0xFF;\n"+
                "  PJDIR = 0xFF;\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();\n"+
                "}\n";


        static String program17=   "//   MSP430F543xA Demo - Enters LPM4;  LFXT1, REF0 disabled, SVS disabled\n"+
                "//\n"+
                "//   Description: Configure ports and enter LPM4. Measure current (~1.1uA).\n"+
                "//   MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW | WDTHOLD;                 // Stop WDT\n"+

                " // Setup UCS\n"+
                "  UCSCTL4 = SELA_1;                       // Ensure VLO is ACLK source \n"+

                " // Port Configuration\n"+
                "  P1OUT = 0x00;\n"+
                "  P2OUT = 0x00;\n"+
                "  P3OUT = 0x00;\n"+
                "  P4OUT = 0x00;\n"+
                "  P5OUT = 0x00;\n"+
                "  P6OUT = 0x00;\n"+
                "  P7OUT = 0x00;\n"+
                "  P8OUT = 0x00;\n"+
                "  P9OUT = 0x00;\n"+
                "  P10OUT = 0x00;\n"+
                "  P11OUT = 0x00;\n"+
                "  PJOUT = 0x00;\n"+

                "  P1DIR = 0xFF;\n"+
                "  P2DIR = 0xFF;\n"+
                "  P3DIR = 0xFF;\n"+
                "  P4DIR = 0xFF;\n"+
                "  P5DIR = 0xFF;\n"+
                "  P6DIR = 0xFF;\n"+
                "  P7DIR = 0xFF;\n"+
                "  P8DIR = 0xFF;\n"+
                "  P9DIR = 0xFF;\n"+
                "  P10DIR = 0xFF;\n"+
                "  P11DIR = 0xFF;\n"+
                "  PJDIR = 0xFF;\n"+

                "  // Disable SVS\n"+
                "  PMMCTL0_H = PMMPW_H;                    // PMM Password\n"+
                "  SVSMHCTL &= ~(SVMHE | SVSHE);           // Disable High side SVS\n"+
                "  SVSMLCTL &= ~(SVMLE | SVSLE);           // Disable Low side SVS\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM3\n"+
                "  __no_operation();\n"+

                "}\n";


        static String program18=  "//    MSP430F543xA Demo - 16x16 Unsigned Multiply\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RESLO and RESHI.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY = 0x1234;                             // Load first operand -unsigned mult\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4 \n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";



        static String program19=   "//   MSP430F543xA Demo - 8x8 Unsigned Multiply\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RESLO and RESHI.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  MPY = 0x12;                               // Load first operand -unsigned mult\n"+
                "  OP2 = 0x56;                               // Load second operand\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program20=   "//   MSP430F543xA Demo - 16x16 Signed Multiply\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RESLO, RESHI and SUMEXT = FFFF if result is\n"+
                "//   negative, SUMEXT = 0 otherwise. Result is also stored as Result variable.\n"+

                "#include <msp430.h>\n"+
                " signed int multiplier = 0x1234;\n"+
                "  signed int operand = -6578;\n"+
                "  signed long result;                       // global variable for multiply result\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  MPYS = multiplier;                        // Load first operand -signed mult\n"+
                "  OP2 = operand;                            // Load second operand\n"+

                "  result = RESHI;                           // Load RESHI word result\n"+
                "  result = (result<<16)|RESLO;              // Shift RESHI left and concat with\n"+
                "                                            // RESLO word result\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger \n"+
                "}\n";


        static String program21=  "//   MSP430F543xA Demo - 8x8 Signed Multiply\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RESLO, RESHI and SUMEXT = FFFF if result is\n"+
                "//   negative, SUMEXT = 0 otherwise.\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  unsigned char value1,value2;\n"+

                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  value1 = 0x04;                            // Assign operands for signed mult\n"+
                "  value2 = 0x84;\n"+

                "  MPYS_B = value1;                          // Load MPYS to denote signed operation\n"+
                "  OP2_B = value2;                           // Load OP2 with byte access to avoid\n"+
                "                                            // the need for sign extension\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program22=    "//   MSP430F543xA Demo - 16x16 Unsigned Multiply Accumulate\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. A second multiply accumulate operation is performed after that.\n"+
                "//   Results are stored in RESLO and RESHI. SUMEXT contains the carry of the\n"+
                "//   result.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY = 0x1234;                             // Load 1st operand - unsigned mult\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+

                "  MAC = 0x1234;                             // Load first operand - unsigned MAC\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";


        static String program23=   "//   MSP430F543xA Demo - 8x8 Unsigned Multiply Accumulate\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. A second multiply accumulate operation is performed after that.\n"+
                "//   Results are stored in RESLO and RESHI. SUMEXT contains the carry of the\n"+
                "//   result.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY = 0x12;                               // Load first operand -unsigned mult\n"+
                "  OP2 = 0x56;                               // Load second operand\n"+

                "  MAC = 0x12;                               // Load first operand -unsigned MAC\n"+
                "  OP2 = 0x56;                               // Load second operand\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";


        static String program24=   "//   MSP430F543xA Demo - 16x16 Signed Multiply Accumulate\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded.  A second multiply-accumulate operation is performed after that.\n"+
                "//   Results are stored in RESLO and RESHI.  SUMEXT contains the extended sign\n"+
                "//   of the result.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  MPY = 0x1234;                             // Load first operand -unsigned mult\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+

                "  MACS = 0x1234;                            // Load first operand -signed MAC\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";



        static String program25=  "//   MSP430F543xA Demo - 8x8 Signed Multiply Accumulate\n"+
                "//\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded.  A second multiply accumulate operation is performed after that.\n"+
                "//   Results are stored in RESLO and RESHI.  SUMEXT contains the extended sign of\n"+
                "//   result.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  unsigned char value1,value2;\n"+

                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  MPY = 0x1234;                             // Load 1st operand - unsigned mult\n"+
                "  OP2 = 0x5678;                             // Load second operand\n"+
                "  value1 = 0x12;                            // Load first operand - signed MAC\n"+
                "  value2 = 0x16;                            // Load second operand\n"+
                "  MACS_B = value1;                          // Signed multiplication\n"+
                "  OP2_B = value2;                           // Byte access removes the need for\n"+
                "                                            // ... sign extension\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";


        static String program26=  "//   MSP430F543xA Demo - 32x32 Unsigned Multiply\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RES0, RES1, RES2 and RES3.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY32L = 0x1234;                          // Load lower 16 bits of operand 1\n"+
                "  MPY32H = 0x1234;                          // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program27=   "//   MSP430F543xA Demo - 32x32 Signed Multiply\n"+
                "//   Description: Hardware multiplier is used to multiply two numbers.\n"+
                "//   The calculation is automatically initiated after the second operand is\n"+
                "//   loaded. Results are stored in RES0, RES1, RES2 and RES3.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPYS32L = 0x1234;                         // Load lower 16 bits of operand 1\n"+
                "  MPYS32H = 0x1234;                         // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program28=   "//  MSP430F543xA Demo - 32x32 Signed Multiply Accumalate\n"+
                "//  Description: Hardware multiplier is used to multiply-accumalate a set of\n"+
                "//  numbers. The first calculation is automatically initiated after the second\n"+
                "//  operand is loaded. A second multiply-accumulate operation is performed next.\n"+
                "//  Results are stored in RES0, RES1, RES2 and RES3.\n"+
                "//  SUMEXT contains the extended sign of the result.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "                                            // unsigned multiply\n"+
                "  MPY32L = 0x1234;                          // Load lower 16 bits of operand 1\n"+
                "  MPY32H = 0x1234;                          // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();                                   // signed MAC\n"+
                "  MACS32L = 0x1234;                         // Load lower 16 bits of operand 1\n"+
                "  MACS32H = 0x1234;                         // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program29= "//  MSP430F543xA Demo - 32x32 Unsigned Multiply Accumalate\n"+
                "//  Description: Hardware multiplier is used to multiply-accumalate a set of\n"+
                "//  numbers. The first calculation is automatically initiated after the second\n"+
                "//  operand is loaded. A second multiply-accumulate operation is performed next.\n"+
                "//  Results are stored in RES0, RES1, RES2 and RES3.\n"+
                "//  SUMEXT contains the extended sign of the result.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                // unsigned multiply\n"+
                "  MPY32L = 0x1234;                          // Load lower 16 bits of operand 1\n"+
                "  MPY32H = 0x1234;                          // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "                                            // unsigned MAC\n"+
                "  MAC32L = 0x1234;                          // Load lower 16 bits of operand 1\n"+
                "  MAC32H = 0x1234;                          // Load upper 16 bits of operand 1\n"+

                "  OP2L = 0x5678;                            // Load lower 16 bits of operand 2\n"+
                "  OP2H = 0x5678;                            // Load upper 16 bits of operand 2\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";


        static String program30=   "//   MSP430F543xA Demo - Saturation mode overflow test\n"+
                "//   Description: The example illustrates a special case showing overflow.\n"+
                "//   The addition result of 2 positive numbers may exceed the highest positive\n"+
                "//   number (0x7FFF FFFF for 32 bit result) due to overflow indicating a negative\n"+
                "//   result. By having the saturation mode enabled, this result can be truncated\n"+
                "//   off to this highest positive number. Results with and without saturation\n"+
                "//   mode are shown.\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "unsigned int Result_lower16;\n"+
                "unsigned int Result_upper16;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY32CTL0 = MPYSAT;                       // Enable saturation mode\n"+

                "  RES3 = 0;\n"+
                "  RES2 = 0;\n"+
                "//------32-bit Result of a multiply operation (not shown) in RES0 and RES1------\n"+
                "  RES1 = 0x7FFF;                            // Pre-load result registers\n"+
                "  RES0 = 0xFFFD;\n"+

                "  MACS = 0x05;                              // Add 5 to previous result\n"+
                "  OP2 = 0x01;\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+

                "  Result_upper16 = RESHI;                   // Result_upper15 = 0x7FFF\n"+
                "  Result_lower16 = RESLO;                   // Result_lower15 = 0xFFFF\n"+

                "  MPY32CTL0 &= ~MPYSAT;                     // Clear saturation mode\n"+

                "  __bis_SR_register(LPM4_bits);             // LPM4\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";



        static String program31=     "//    MSP430F543xA Demo - Saturation mode underflow test\n"+
                "//\n"+
                "//   Description: The example illustrates a special case showing underflow.\n"+
                "//   Underflow occurs when adding 2 negative numbers yields a positive result.\n"+
                "//   By having the saturation mode enabled, the result if rounded off to the\n"+
                "//   highest negative number (0x8000.0000 for 16 bit). Results can be viewed\n"+
                "//   in the debugger window.\n"+
                "//\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "unsigned int Result_lower16;\n"+
                "unsigned int Result_upper16;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY32CTL0 = MPYSAT+MPYC;                  // Saturation mode enable,\n"+
                "                                            // Carry bit set\n"+
                "  RES3 = 0;\n"+
                "  RES2 = 0;\n"+
                "  RES1 = 0x0000;                            // Pre-load first negative value\n"+
                "  RES0 = 0x8000;\n"+

                "  MACS = 0x8000;                            // Add to second negative value\n"+
                "  OP2 = 0x05;\n"+
                "  __no_operation();                         // Wait for the result to become ready\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+
                "  __no_operation();\n"+

                "  Result_upper16 = RESHI;                   // Result_upper15 = 0x8000\n"+
                "  Result_lower16 = RESLO;                   // Result_lower15 = 0x0000\n"+

                "  MPY32CTL0 &= ~MPYSAT;                     // Clear saturation mode\n"+

                "  __bis_SR_register(LPM4_bits);             // LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program32=     "//   MSP430F543xA Demo - Fractional mode, Q15 multiplication\n"+
                "//\n"+
                "//   Description: The example illustrates multiplication of 2 Q15 numbers in\n"+
                "//   fractional mode. The result is a Q15 (15 bit) number stored in the RES1\n"+
                "//   register. It can be viewed in the debugger window.\n"+
                "//\n"+
                "//\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "unsigned int Result_Q15;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  MPY32CTL0 = MPYFRAC;                      // Set fractional mode\n"+

                "  MPYS = 0x7D70;                            // Load first operand\n"+
                "  OP2 = 0x1000;                             // Load second operand\n"+

                "  Result_Q15 = RESHI;                       // Q15 result\n"+

                "  MPY32CTL0 &= ~MPYFRAC;\n"+

                "  __bis_SR_register(LPM4_bits);             // Enter LPM4\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program33=   "//  MSP430F543xA Demo - LFXT1 Oscillator Fault Detection\n"+
                "//\n"+
                "//  Description: System runs normally in LPM3 with with basic timer clocked by\n"+
                "//  32kHz ACLK with a 1 second interrupt. P1.0 is normally toggled every\n"+
                "//  1 second inside basic timer interrupt. If an LFXT1 oscillator fault occurs,\n"+
                "//  NMI is requested forcing exit from LPM3. P1.0 is toggled rapidly by\n"+
                "//  software as long as LFXT1 oscillator fault is present. Assuumed only\n"+
                "//  LFXT1 as NMI source - code does not check for other NMI sources.\n"+
                "//  ACLK = LFXT1 = 32768Hz, MCLK = SMCLK = default DCO = 32 x ACLK = 1048576Hz\n"+
                "//  //* An external watch crystal between XIN & XOUT is required for ACLK *//	\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "volatile unsigned int i;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+

                "  // Set up XT1\n"+
                "  P7SEL |= 0x03;                            // Analog function for XT1 Pins\n"+
                "  UCSCTL6 &= ~(XT1OFF);                     // XT1 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  P1DIR |= BIT0;                            // P1.0 output\n"+

                "  RTCCTL01 = RTCTEV_3;\n"+
                "  RTCPS0CTL = RT0PSDIV_7;                   // Set RTPS0 to /256\n"+
                "  RTCPS1CTL = RT1IP_6 + RT1PSIE + RT1SSEL_3;// Set RT1IP to /4, enable\n"+
                "                                            // RT1PS interrupt and select\n"+
                "                                            // RTOPS output as clock\n"+

                "  SFRIE1 = OFIE;                            // Enable osc fault interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+


                "// RTC Interrupt Service Routine\n"+
                "#pragma vector=RTC_VECTOR\n"+
                "__interrupt void RTC_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+
                "  RTCCTL01 &= ~RTCTEVIFG;\n"+
                "  RTCPS1CTL &= ~RT1PSIFG;\n"+
                "}\n"+

                "#pragma vector=UNMI_VECTOR\n"+
                "__interrupt void UNMI_ISR(void)\n"+
                "{\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT1LFOFFG + DCOFFG);       // Clear XT1 & DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear OSC Fault flag\n"+
                "    for (i = 0xFFFF; i > 0; i--);           // Time for flag to set\n"+
                "    P1OUT ^= BIT0;                          // Toggle P1.0 using exclusive-OR\n"+
                "  }while ( (SFRIFG1 & OFIFG) );\n"+
                "}\n";

        static String program34=    "//  MSP430F543xA Demo - Software Poll P1.4, Set P1.0 if P1.4 = 1\n"+
                "//\n"+
                "//  Description: Poll P1.4 in a loop, if hi P1.0 is set, if low, P1.0 reset.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+

                "  while (1)                                 // Test P1.4\n"+
                "  {\n"+
                "    if (0x010 & P1IN)\n"+
                "      P1OUT |= 0x01;                        // if P1.4 set, set P1.0\n"+
                "    else\n"+
                "      P1OUT &= ~0x01;                       // else reset\n"+
                "  }\n"+
                "}\n";

        static String program35=   "//  MSP430F543xA Demo - Software Port Interrupt Service on P1.4 from LPM4 with\n"+
                "//                    Internal Pull-up Resistance Enabled\n"+
                "//\n"+
                "//  Description: A hi TO low transition on P1.4 will trigger P1_ISR which,\n"+
                "//  toggles P1.0. P1.4 is internally enabled to pull-up. Normal mode is\n"+
                "//  LPM4 ~ 0.1uA. LPM4 current can be measured with the LED removed, all\n"+
                "//  unused Px.x configured as output or inputs pulled high or low.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  P1REN |= 0x10;                            // Enable P1.4 internal resistance\n"+
                "  P1OUT |= 0x10;                            // Set P1.4 as pull-Up resistance\n"+
                "  P1IE |= 0x10;                             // P1.4 interrupt enabled\n"+
                "  P1IES |= 0x10;                            // P1.4 Hi/Lo edge\n"+
                "  P1IFG &= ~0x10;                           // P1.4 IFG cleared\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4 w/interrupt\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Port 1 interrupt service routine\n"+
                "#pragma vector=PORT1_VECTOR\n"+
                "__interrupt void Port_1(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // P1.0 = toggle\n"+
                "  P1IFG &= ~0x010;                          // P1.4 IFG cleared\n"+
                "}\n";

        static String program36=   "//   MSP430F543xA Demo - Write a byte to Port 1\n"+
                "//\n"+
                "//   Description: Writes a byte(0xFF) to Port 1 and stays in LPM4 mode\n"+
                "//   ACLK = n/a, MCLK = SMCLK = default DCO\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+
                "  P1DIR = 0x0FF;                            // Set P1.x to output direction\n"+
                "  P1OUT = 0x0FF;                            // Set all P1 pins HI\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4 w/interrupt\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";

        static String program37=   "//   MSP430F543xA Demo - Write a Word to Port A (Port1+Port2)\n"+
                "//\n"+
                "//   Description: Writes a Word(FFFFh) to Port A and stays in LPM4\n"+
                "//   ACLK = 32.768kHz, MCLK = SMCLK = default DCO\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  PADIR |= 0xFFFF;                          // PA.x output\n"+
                "  PAOUT |= 0xFFFF;                          // Set all PA pins HI\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4 w/interrupts enabled\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n";


        static String program38=   "//  MSP430F543xA Demo - RTC in Counter Mode toggles P1.0 every 1s\n"+
                "//\n"+
                "//  This program demonstrates RTC in counter mode configured to source from ACLK\n"+
                "//  to toggle P1.0 LED every 1s.\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;\n"+

                "  P1OUT |= 0x01;\n"+
                "  P1DIR |= 0x01;\n"+

                "  // Setup RTC Timer\n"+
                "  RTCCTL01 = RTCTEVIE + RTCSSEL_2 + RTCTEV_0; // Counter Mode, RTC1PS, 8-bit ovf\n"+
                // overflow interrupt enable\n"+
                "  RTCPS0CTL = RT0PSDIV_2;                   // ACLK, /8, start timer\n"+
                "  RTCPS1CTL = RT1SSEL_2 + RT1PSDIV_3;       // out from RT0PS, /16, start timer\n"+

                "  __bis_SR_register(LPM3_bits + GIE);\n"+
                "}\n"+

                "#pragma vector=RTC_VECTOR\n"+
                "__interrupt void RTC_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(RTCIV,16))\n"+
                "  {\n"+
                "    case 0: break;                          // No interrupts\n"+
                "    case 2: break;                          // RTCRDYIFG\n"+
                "    case 4:                                 // RTCEVIFG\n"+
                "      P1OUT ^= 0x01;\n"+
                "      break;\n"+
                "    case 6: break;                          // RTCAIFG\n"+
                "    case 8: break;                          // RT0PSIFG\n"+
                "    case 10: break;                         // RT1PSIFG\n"+
                "    case 12: break;                         // Reserved\n"+
                "    case 14: break;                         // Reserved\n"+
                "    case 16: break;                         // Reserved\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";

        static String program39=  "//  MSP430F54xA Demo - RTC_A in real time clock mode\n"+
                "//\n"+
                "//  Description: This program demonstrates the RTC mode by triggering an\n"+
                "//  interrupt every second and minute. This code toggles P1.0 every second.\n"+
                "//  This code recommends an external LFXT1 crystal for RTC accuracy.\n"+
                "//  ACLK = LFXT1 = 32768Hz, MCLK = SMCLK = default DCO = 32 x ACLK = 1048576Hz\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop Watchdog Timer\n"+
                "  P1DIR |= BIT0;                            // Set P1.0 as output\n"+

                "  // Initialize LFXT1\n"+
                "  P7SEL |= 0x03;                            // Select XT1\n"+
                "  UCSCTL6 &= ~(XT1OFF);                     // XT1 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  // Loop until XT1,XT2 & DCO fault flag is cleared\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  // Configure RTC_A\n"+
                "  RTCCTL01 |= RTCTEVIE + RTCRDYIE + RTCBCD + RTCHOLD + RTCMODE;\n"+
                "                                            // RTC enable, BCD mode, RTC hold\n"+
                "                                            // enable RTC read ready interrupt\n"+
                "                                            // enable RTC time event interrupt\n"+

                "  RTCYEAR = 0x2010;                         // Year = 0x2010\n"+
                "  RTCMON = 0x4;                             // Month = 0x04 = April\n"+
                "  RTCDAY = 0x05;                            // Day = 0x05 = 5th\n"+
                "  RTCDOW = 0x01;                            // Day of week = 0x01 = Monday\n"+
                "  RTCHOUR = 0x10;                           // Hour = 0x10\n"+
                "  RTCMIN = 0x32;                            // Minute = 0x32\n"+
                "  RTCSEC = 0x45;                            // Seconds = 0x45\n"+

                "  RTCADOWDAY = 0x2;                         // RTC Day of week alarm = 0x2\n"+
                "  RTCADAY = 0x20;                           // RTC Day Alarm = 0x20\n"+
                "  RTCAHOUR = 0x10;                          // RTC Hour Alarm\n"+
                "  RTCAMIN = 0x23;                           // RTC Minute Alarm\n"+

                "  RTCCTL01 &= ~(RTCHOLD);                   // Start RTC calendar mode\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 mode with interrupts\n"+
                "                                            // enabled\n"+
                "  __no_operation();\n"+
                "}\n"+

                "#pragma vector=RTC_VECTOR\n"+
                "__interrupt void RTC_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(RTCIV,16))\n"+
                " {\n"+
                "    case RTC_NONE:                          // No interrupts\n"+
                "      break;\n"+
                "    case RTC_RTCRDYIFG:                     // RTCRDYIFG\n"+
                "      P1OUT ^= 0x01;                        // Toggles P1.0 every second\n"+
                "      break;\n"+
                "    case RTC_RTCTEVIFG:                     // RTCEVIFG\n"+
                "      __no_operation();                     // Interrupts every minute\n"+
                "      break;\n"+
                "    case RTC_RTCAIFG:                       // RTCAIFG\n"+
                "      break;\n"+
                "    case RTC_RT0PSIFG:                      // RT0PSIFG\n"+
                "      break;\n"+
                "    case RTC_RT1PSIFG:                      // RT1PSIFG\n"+
                "      break;\n"+
                "    case 12: break;                         // Reserved\n"+
                "    case 14: break;                         // Reserved\n"+
                "    case 16: break;                         // Reserved\n"+
                "    default: break; \n"+
                "  } \n"+
                "}\n";



        static String program40=   "//  MSP430F543xA Demo - Timer_A3, Toggle P1.0, CCR0 Cont. Mode ISR, DCO SMCLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and TA_0 ISR. Toggles every\n"+
                "//  50000 SMCLK cycles. SMCLK provides clock source for TACLK.\n"+
                "//  During the TA_0 ISR, P1.0 is toggled and 50000 clock cycles are added to\n"+
                "//  CCR0. TA_0 ISR is triggered every 50000 cycles. CPU is normally off and\n"+
                "//  used only during TA_ISR.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TACLK = default DCO ~1.045MHz\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TA1CCTL0 = CCIE;                          // CCR0 interrupt enabled\n"+
                "  TA1CCR0 = 50000;\n"+
                "  TA1CTL = TASSEL_2 + MC_2 + TACLR;         // SMCLK, contmode, clear TAR\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer A0 interrupt service routine\n"+
                "#pragma vector=TIMER1_A0_VECTOR\n"+
                "__interrupt void TIMER1_A0_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+
                "  TA1CCR0 += 50000;                         // Add Offset to CCR0\n"+
                "}\n";

        static String program41=    "//  MSP430F543xA Demo - Timer_A3, Toggle P1.0, CCR0 Up Mode ISR, DCO SMCLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and TA_1 ISR. Timer1_A is\n"+
                "//  configured for up mode, thus the timer overflows when TAR counts\n"+
                "//  to CCR0. In this example, CCR0 is loaded with 50000.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TACLK = default DCO ~1.045MHz\n"+



                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TA1CCTL0 = CCIE;                          // CCR0 interrupt enabled\n"+
                "  TA1CCR0 = 50000;\n"+
                "  TA1CTL = TASSEL_2 + MC_1 + TACLR;         // SMCLK, upmode, clear TAR\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer A0 interrupt service routine\n"+
                "#pragma vector=TIMER1_A0_VECTOR\n"+
                "__interrupt void TIMER1_A0_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+
                "}\n";

        static String program42=     "//  MSP430F543xA Demo - Timer_A3, Toggle P1.0, Overflow ISR, DCO SMCLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and Timer1_A overflow ISR.\n"+
                "//  In this example an ISR triggers when TA overflows. Inside the TA\n"+
                "//  overflow ISR P1.0 is toggled. Toggle rate is approximatlely 16.8Hz.\n"+
                "//  Proper use of the TAIV interrupt vector generator is demonstrated.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TACLK = default DCO ~1.05MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TA1CTL = TASSEL_2 + MC_2 + TACLR + TAIE;  // SMCLK, contmode, clear TAR\n"+
                "                                            // enable interrupt\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer_A3 Interrupt Vector (TAIV) handler\n"+
                "#pragma vector=TIMER1_A1_VECTOR\n"+
                "__interrupt void TIMER1_A1_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(TA1IV,14))\n"+
                "  {\n"+
                "    case  0: break;                          // No interrupt\n"+
                "    case  2: break;                          // CCR1 not used\n"+
                "    case  4: break;                          // CCR2 not used\n"+
                "    case  6: break;                          // reserved\n"+
                "    case  8: break;                          // reserved\n"+
                "    case 10: break;                          // reserved\n"+
                "    case 12: break;                          // reserved\n"+
                "    case 14: P1OUT ^= 0x01;                  // overflow\n"+
                "             break;\n"+
                "    default: break; \n"+
                "  }\n"+
                "}\n";

        static String program43=   "//  MSP430F543xA Demo - Timer_A3, Toggle P1.0, Overflow ISR, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and the Timer1_A overflow ISR.\n"+
                "//  In this example an ISR triggers when TA overflows. Inside the ISR P1.0\n"+
                "//  is toggled. Toggle rate is exactly 0.5Hz. Proper use of the TAIV interrupt\n"+
                "//  vector generator is demonstrated.\n"+
                "//  ACLK = TACLK = 32768Hz, MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TA1CTL = TASSEL_1 + MC_2 + TACLR + TAIE;  // ACLK, contmode, clear TAR\n"+
                "                                            // enable interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer_A3 Interrupt Vector (TAIV) handler\n"+
                "#pragma vector=TIMER1_A1_VECTOR\n"+
                "__interrupt void TIMER1_A1_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(TA1IV,14))\n"+
                "  {\n"+
                "    case  0: break;                          // No interrupt\n"+
                "    case  2: break;                          // CCR1 not used\n"+
                "    case  4: break;                          // CCR2 not used\n"+
                "    case  6: break;                          // reserved\n"+
                "    case  8: break;                          // reserved\n"+
                "    case 10: break;                          // reserved\n"+
                "    case 12: break;                          // reserved\n"+
                "    case 14: P1OUT ^= 0x01;                  // overflow\n"+
                "             break;\n"+
                "    default: break; \n"+
                "  }\n"+
                "}\n";

        static String program44=  "//  MSP430F543xA Demo - Timer_A3, Toggle P1.0, CCR0 Up Mode ISR, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and the TA_1 ISR. Timer1_A is\n"+
                "//  configured for up mode, thus the timer overflows when TAR counts\n"+
                "//  to CCR0. In this example, CCR0 is loaded with 1000-1.\n"+
                "//  Toggle rate = 32768/(2*1000) = 16.384\n"+
                "//  ACLK = TACLK = 32768Hz, MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                " P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TA1CCTL0 = CCIE;                          // CCR0 interrupt enabled\n"+
                "  TA1CCR0 = 1000-1;\n"+
                "  TA1CTL = TASSEL_1 + MC_1 + TACLR;         // ACLK, upmode, clear TAR\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer A0 interrupt service routine\n"+
                "#pragma vector=TIMER1_A0_VECTOR\n"+
                "__interrupt void TIMER1_A0_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+
                "}\n";




        static String program45=  "//  MSP430F5438A Demo - Timer_A3, Toggle P1.0;P2.1-3, Cont. Mode ISR, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Use Timer1_A CCRx units and overflow to generate four\n"+
                "//  independent timing intervals. For demonstration, CCR0, CCR1 and CCR2\n"+
                "//  output units are optionally selected with port pins P2.1, P2.2 and P2.3\n"+
                "//  in toggle mode. As such, these pins will toggle when respective CCRx\n"+
                "//  registers match the TAR counter. Interrupts are also enabled with all\n"+
                "//  CCRx units, software loads offset to next interval only - as long as\n"+
                "//  the interval offset is added to CCRx, toggle rate is generated in\n"+
                "//  hardware. Timer1_A overflow ISR is used to toggle P1.0 with software.\n"+
                "//  Proper use of the TAIV interrupt vector generator is demonstrated.\n"+
                "//  ACLK = TACLK = 32kHz, MCLK = SMCLK = default DCO ~1.045MHz\n"+
                "//\n"+
                "//  As coded and with TACLK = 32768Hz, toggle rates are:\n"+
                "//  P2.1= CCR0 = 32768/(2*4) = 4096Hz\n"+
                "//  P2.2= CCR1 = 32768/(2*16) = 1024Hz\n"+
                "//  P2.3= CCR2 = 32768/(2*100) = 163.84Hz\n"+
                "//  P1.0= overflow = 32768/(2*65536) = 0.25Hz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2SEL |= 0x0E;                            // P1.1 - P1.3 option select\n"+
                "  P2DIR |= 0x0E;                            // P2.1 - P2.3 outputs\n"+
                "  P1DIR |= 0x01;                            // P1.0 - Outputs\n"+
                "  TA1CCTL0 = OUTMOD_4 + CCIE;               // CCR0 toggle, interrupt enabled\n"+
                "  TA1CCTL1 = OUTMOD_4 + CCIE;               // CCR1 toggle, interrupt enabled\n"+
                "  TA1CCTL2 = OUTMOD_4 + CCIE;               // CCR2 toggle, interrupt enabled\n"+
                "  TA1CTL = TASSEL_1 + MC_2 + TACLR + TAIE;  // ACLK, contmode, clear TAR,\n"+
                "                                            // interrupt enabled\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer1 A0 interrupt service routine\n"+
                "#pragma vector=TIMER1_A0_VECTOR\n"+
                "__interrupt void Timer_A0 (void)\n"+
                "{\n"+
                "  TA1CCR0 += 4;                             // Add Offset to CCR0\n"+
                "}\n"+

                "// Timer_A3 Interrupt Vector (TAIV) handler\n"+
                "#pragma vector=TIMER1_A1_VECTOR\n"+
                "__interrupt void TIMER1_A1_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(TA1IV,14))\n"+
                "  {\n"+
                "    case 0: break;                  \n"+
                "    case 2:  TA1CCR1 += 16;                 // Add Offset to CCR1\n"+
                "             break;\n"+
                "    case 4:  TA1CCR2 += 100;                // Add Offset to CCR2\n"+
                "             break;\n"+
                "    case 6:  break;                         // CCR3 not used\n"+
                "    case 8:  break;                         // CCR4 not used\n"+
                "    case 10: break;                         // CCR5 not used\n"+
                "    case 12: break;                         // Reserved not used\n"+
                "    case 14: P1OUT ^= 0x01;                 // overflow\n"+
                "             break;\n"+
                "    default: break;\n"+
                " }\n"+
                "}\n";



        static String program46=    "//  MSP430F543xA Demo - Timer_A3, Toggle P2.1/TA1.0, Up Mode, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Toggle P2.1 using hardware TA1.0 output. Timer1_A is configured\n"+
                "//  for up mode with CCR0 defining period, TA1.0 also output on P2.1. In this\n"+
                "//  example, CCR0 is loaded with 10-1 and TA1.0 will toggle P1.1 at TACLK/10.\n"+
                "//  Thus the output frequency on P1.1 will be the TACLK/20. No CPU or software\n"+
                "//  resources required. Normal operating mode is LPM3.\n"+
                "//  As coded with TACLK = ACLK, P2.1 output frequency = 32768/20 = 1.6384kHz.\n"+
                "//  ACLK = TACLK = 32kHz, MCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x02;                            // P2.1 output\n"+
                "  P2SEL |= 0x02;                            // P2.1 option select\n"+
                "  TA1CCTL0 = OUTMOD_4;                      // CCR0 toggle mode\n"+
                "  TA1CCR0 = 10-1;\n"+
                "  TA1CTL = TASSEL_1 + MC_1 + TACLR;         // ACLK, upmode, clear TAR\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program47=    "//  MSP430F543xA Demo - Timer_A3, Toggle P2.1/TA1.0, Up/Down Mode, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Toggle P2.1 using hardware TA1.0 output. Timer1_A is configured\n"+
                "//  for up/down mode with CCR0 defining period, TA1.0 also output on P2.1. In\n"+
                "//  this example, CCR0 is loaded with 5 and TA1.0 will toggle P2.1 at TACLK/2*5.\n"+
                "//  Thus the output frequency on P2.1 will be the TACLK/20. No CPU or software\n"+
                "//  resources required. Normal operating mode is LPM3.\n"+
                "//  As coded with TACLK = ACLK, P2.1 output frequency = 32768/20 = 1.6384kHz\n"+
                "//  ACLK = TACLK = 32kHz, MCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x02;                            // P2.1 output\n"+
                "  P2SEL |= 0x02;                            // P2.1 option select\n"+
                "  TA1CCTL0 = OUTMOD_4;                      // CCR0 toggle mode\n"+
                "  TA1CCR0 = 5;\n"+
                "  TA1CTL = TASSEL_1 + MC_3 + TACLR;         // ACLK, up-downmode, clear TAR\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";


        static String program48=     "//  MSP430F543xA Demo - Timer_A3, PWM TA1.1-2, Up Mode, DCO SMCLK\n"+
                "//\n"+
                "//  Description: This program generates two PWM outputs on P2.2,P2.3 using\n"+
                "//  Timer1_A configured for up mode. The value in CCR0, 512-1, defines the PWM\n"+
                "//  period and the values in CCR1 and CCR2 the PWM duty cycles. Using ~1.045MHz\n"+
                "//  SMCLK as TACLK, the timer period is ~500us with a 75% duty cycle on P2.2\n"+
                "//  and 25% on P2.3.\n"+
                "//  ACLK = n/a, SMCLK = MCLK = TACLK = default DCO ~1.045MHz.\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x0C;                            // P2.2 and P2.3 output\n"+
                "  P2SEL |= 0x0C;                            // P2.2 and P2.3 options select\n"+
                "  TA1CCR0 = 512-1;                          // PWM Period\n"+
                "  TA1CCTL1 = OUTMOD_7;                      // CCR1 reset/set\n"+
                "  TA1CCR1 = 384;                            // CCR1 PWM duty cycle\n"+
                "  TA1CCTL2 = OUTMOD_7;                      // CCR2 reset/set\n"+
                "  TA1CCR2 = 128;                            // CCR2 PWM duty cycle\n"+
                "  TA1CTL = TASSEL_2 + MC_1 + TACLR;         // SMCLK, up mode, clear TAR\n"+

                "  __bis_SR_register(LPM0_bits);             // Enter LPM0\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";




        static String program49=       "//  MSP430F543xA Demo - Timer_A3, PWM TA1.1-2, Up Mode, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: This program generates two PWM outputs on P2.2,P2.3 using\n"+
                "//  Timer1_A configured for up mode. The value in CCR0, 512-1, defines the PWM\n"+
                "//  period and the values in CCR1 and CCR2 the PWM duty cycles. Using 32kHz\n"+
                "//  ACLK as TACLK, the timer period is ~ (512/32k) ~ 15.6ms with a 75% duty\n"+
                "//  cycle on P2.2 and 25% on P2.3. Normal operating mode is LPM3.\n"+
                "//  ACLK = TACLK = LFXT1 = 32768Hz, MCLK = default DCO ~1.045MHz.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x0C;                            // P2.2 and P2.3 output\n"+
                "  P2SEL |= 0x0C;                            // P2.2 and P2.3 options select\n"+
                "  TA1CCR0 = 512-1;                          // PWM Period\n"+
                "  TA1CCTL1 = OUTMOD_7;                      // CCR1 reset/set\n"+
                "  TA1CCR1 = 384;                            // CCR1 PWM duty cycle\n"+
                "  TA1CCTL2 = OUTMOD_7;                      // CCR2 reset/set\n"+
                "  TA1CCR2 = 128;                            // CCR2 PWM duty cycle\n"+
                "  TA1CTL = TASSEL_1 + MC_1 + TACLR;         // ACLK, up mode, clear TAR\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program50=     "//  MSP430F543xA Demo - Timer_A3, PWM TA1.1-2, Up/Down Mode, DCO SMCLK\n"+
                "//\n"+
                "//  Description: This program generates two PWM outputs on P2.2,3 using\n"+
                "//  Timer1_A configured for up/down mode. The value in CCR0, 128, defines the\n"+
                "//  PWM period/2 and the values in CCR1 and CCR2 the PWM duty cycles. Using\n"+
                "//  ~1.045MHz SMCLK as TACLK, the timer period is ~233us with a 75% duty cycle\n"+
                "//  on P2.2 and 25% on P2.3.\n"+
                "//  SMCLK = MCLK = TACLK = default DCO ~1.045MHz.\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x0C;                            // P2.2 and P2.3 output\n"+
                "  P2SEL |= 0x0C;                            // P2.2 and P2.3 options select\n"+
                "  TA1CCR0 = 128;                            // PWM Period/2\n"+
                "  TA1CCTL1 = OUTMOD_6;                      // CCR1 toggle/set\n"+
                "  TA1CCR1 = 32;                             // CCR1 PWM duty cycle\n"+
                "  TA1CCTL2 = OUTMOD_6;                      // CCR2 toggle/set\n"+
                "  TA1CCR2 = 96;                             // CCR2 PWM duty cycle\n"+
                "  TA1CTL = TASSEL_2 + MC_3 + TACLR;         // SMCLK, up-down mode, clear TAR\n"+

                "  __bis_SR_register(LPM0_bits);             // Enter LPM0\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";




        static String program51=    "//  MSP430F543xA Demo - Timer_A3, PWM TA1.1-2, Up/Down Mode, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: This program generates two PWM outputs on P2.2, P2.3 using\n"+
                "//  Timer1_A configured for up/down mode. The value in CCR0, 128, defines the\n"+
                "//  PWM period/2 and the values in CCR1 and CCR2 the PWM duty cycles. Using\n"+
                "//  32kHz ACLK as TACLK, the timer period is 7.8ms with a 75% duty cycle\n"+
                "//  on P2.2 and 25% on P2.3. Normal operating mode is LPM3\n"+
                "//  ACLK = TACLK = LFXT1 = 32768Hz, MCLK = default DCO ~1.045MHz.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x0C;                            // P2.2 and P2.3 output\n"+
                "  P2SEL |= 0x0C;                            // P2.2 and P2.3 options select\n"+
                "  TA1CCR0 = 128;                            // PWM Period/2\n"+
                "  TA1CCTL1 = OUTMOD_6;                      // CCR1 toggle/set\n"+
                "  TA1CCR1 = 32;                             // CCR1 PWM duty cycle\n"+
                "  TA1CCTL2 = OUTMOD_6;                      // CCR2 toggle/set\n"+
                "  TA1CCR2 = 96;                             // CCR2 PWM duty cycle\n"+
                "  TA1CTL = TASSEL_1 + MC_3;                 // ACLK, up-down mode\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";



        static String program52=  "//  MSP430F543xA Demo - Timer_A3, PWM TA1.1-2, Up/Down Mode, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: This program generates two PWM outputs on P2.2, P2.3 using\n"+
                "//  Timer1_A configured for up/down mode. The value in CCR0, 128, defines the\n"+
                "//  PWM period/2 and the values in CCR1 and CCR2 the PWM duty cycles. Using\n"+
                "//  32kHz ACLK as TACLK, the timer period is 7.8ms with a 75% duty cycle\n"+
                "//  on P2.2 and 25% on P2.3. Normal operating mode is LPM3\n"+
                "//  ACLK = TACLK = LFXT1 = 32768Hz, MCLK = default DCO ~1.045MHz.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P2DIR |= 0x0C;                            // P2.2 and P2.3 output\n"+
                "  P2SEL |= 0x0C;                            // P2.2 and P2.3 options select\n"+
                "  TA1CCR0 = 128;                            // PWM Period/2\n"+
                "  TA1CCTL1 = OUTMOD_6;                      // CCR1 toggle/set\n"+
                "  TA1CCR1 = 32;                             // CCR1 PWM duty cycle\n"+
                "  TA1CCTL2 = OUTMOD_6;                      // CCR2 toggle/set\n"+
                "  TA1CCR2 = 96;                             // CCR2 PWM duty cycle\n"+
                "  TA1CTL = TASSEL_1 + MC_3;                 // ACLK, up-down mode\n"+

                "  __bis_SR_register(LPM3_bits);             // Enter LPM3\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n";




        static String program53=   "//  MSP430F543xA Demo - Timer_B, Toggle P1.0, CCR0 Cont. Mode ISR, DCO SMCLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and TB_0 ISR. Toggles every\n"+
                "//  50000 SMCLK cycles. SMCLK provides clock source for TBCLK.\n"+
                "//  During the TB_0 ISR, P1.0 is toggled and 50000 clock cycles are added to\n"+
                "//  CCR0. TB_0 ISR is triggered every 50000 cycles. CPU is normally off and\n"+
                "//  used only during TB_ISR.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TBCLK = default DCO ~1.045MHz\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                " WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  TBCCTL0 = CCIE;                           // CCR0 interrupt enabled\n"+
                "  TBCCR0 = 50000;\n"+
                "  TBCTL = TBSSEL_2 + MC_2 + TBCLR;          // SMCLK, contmode, clear TBR\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer B0 interrupt service routine\n"+
                "#pragma vector=TIMERB0_VECTOR\n"+
                "__interrupt void TIMERB0_ISR (void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+
                "  TBCCR0 += 50000;                          // Add Offset to CCR0 [Cont mode]\n"+
                "}\n";



        static String program54=   "//  MSP430F543xA Demo - Timer_B, Toggle P1.0, CCR0 Up Mode ISR, DCO SMCLK\n"+
                "//  Description: Toggle P1.0 using software and TB_0 ISR. Timer_B is\n"+
                "//  configured for up mode, thus the timer overflows when TBR counts\n"+
                "//  to CCR0. In this example, CCR0 is loaded with 50000.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TBCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  TBCCTL0 = CCIE;                           // TBCCR0 interrupt enabled\n"+
                "  TBCCR0 = 50000;\n"+
                "  TBCTL = TBSSEL_2 + MC_1 + TBCLR;          // SMCLK, upmode, clear TBR\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer B0 interrupt service routine\n"+
                "#pragma vector=TIMERB0_VECTOR\n"+
                "__interrupt void TIMERB0_ISR (void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0 using exclusive-OR\n"+
                "}\n";




        static String program55= "//  MSP430F543xA Demo - Timer_B, Toggle P1.0, Overflow ISR, DCO SMCLK\n"+
                "//  Description: Toggle P1.0 using software and Timer_B overflow ISR.\n"+
                "//  In this example an ISR triggers when TB overflows.  TB overflow has the\n"+
                "//  lowest priority. Inside the TB overflow ISR P1.0 is toggled. Toggle rate is\n"+
                "//  approximatlely 7.822Hz = [1.045MHz/FFFFh]/2. Proper use of TBIV interrupt vector\n"+
                "//  generator is shown.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = TBCLK = default DCO ~1.045MHz.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  TBCTL = TBSSEL_2 + MC_2 + TBCLR + TBIE;   // SMCLK, contmode, clear TBR\n"+
                "                                            // enable interrupt\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer_B7 Interrupt Vector (TBIV) handler\n"+
                "#pragma vector=TIMERB1_VECTOR\n"+
                "__interrupt void TIMERB1_ISR(void)\n"+
                "{\n"+
                "  /* Any access, read or write, of the TBIV register automatically resets the\n"+
                "  highest 'pending' interrupt flag. */\n"+
                "  switch( __even_in_range(TBIV,14) )\n"+
                "  {\n"+
                "    case  0: break;                          // No interrupt\n"+
                "    case  2: break;                          // CCR1 not used\n"+
                "    case  4: break;                          // CCR2 not used\n"+
                "    case  6: break;                          // CCR3 not used\n"+
                "    case  8: break;                          // CCR4 not used\n"+
                "    case 10: break;                          // CCR5 not used\n"+
                "    case 12: break;                          // CCR6 not used\n"+
                "    case 14: P1OUT ^= 0x01;                  // overflow\n"+
                "            break;\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";



        static String program56=      "//  MSP430F543xA Demo - Timer_B, Toggle P1.0, Overflow ISR, 32kHz ACLK\n"+
                "//  Description: Toggle P1.0 using software and the Timer_B overflow ISR.\n"+
                "//  In this example an ISR triggers when TB overflows. Inside the ISR P1.0\n"+
                "//  is toggled. Toggle rate is exactly 0.25Hz = [32kHz/FFFFh]/2. Proper use of the\n"+
                "//  TBIV interrupt vector generator is demonstrated.\n"+
                "//  ACLK = TBCLK = 32kHz, MCLK = SMCLK = default DCO ~ 1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  TBCTL = TBSSEL_1 + MC_2 + TBCLR + TBIE;   // ACLK, contmode, clear TBR,\n"+
                "                                            // enable interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Timer_B7 Interrupt Vector (TBIV) handler\n"+
                "#pragma vector=TIMERB1_VECTOR\n"+
                "__interrupt void TIMERB1_ISR(void)\n"+
                "{\n"+
                "  /* Any access, read or write, of the TBIV register automatically resets the\n"+
                "  highest 'pending' interrupt flag. */\n"+
                "  switch( __even_in_range(TBIV,14) )\n"+
                "  {\n"+
                "    case  0: break;                          // No interrupt\n"+
                "    case  2: break;                          // CCR1 not used\n"+
                "    case  4: break;                          // CCR2 not used\n"+
                "    case  6: break;                          // CCR3 not used\n"+
                "    case  8: break;                          // CCR4 not used\n"+
                "    case 10: break;                          // CCR5 not used\n"+
                "    case 12: break;                          // CCR6 not used\n"+
                "    case 14: P1OUT ^= 0x01;                  // overflow\n"+
                "            break;\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";



        static String program57=     "//  MSP430F5438A Demo - Timer_B, Toggle P1.0, CCR0 Up Mode ISR, 32kHz ACLK\n"+
                "//\n"+
                "//  Description: Toggle P1.0 using software and the TB_0 ISR. Timer_B is\n"+
                "//  configured for up mode, thus the timer overflows when TBR counts\n"+
                "//  to CCR0. In this example, CCR0 is loaded with 1000-1.\n"+
                "//  Toggle rate = 32768Hz/(2*1000) = 16.384Hz\n"+
                "//  ACLK = TBCLK = 32kHz, MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  TBCCTL0 = CCIE;                           // TRCCR0 interrupt enabled\n"+
                "  TBCCR0 = 1000-1;\n"+
                "  TBCTL = TBSSEL_1 + MC_1 + TBCLR;          // ACLK, upmode, clear TBR\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger  \n"+
                "}\n"+

                "// Timer B0 interrupt service routine\n"+
                "#pragma vector=TIMERB0_VECTOR\n"+
                "__interrupt void TIMERB1_ISR(void)\n"+
                "{\n"+
                "    P1OUT ^= 0x01;                          // Toggle P1.0 using exclusive-OR\n"+
                "}\n";



        static String program58=     "//   MSP430F543xA Demo - Timer_B, PWM TB1-6, Up Mode, DCO SMCLK\n"+
                "//   Description: This program generates six PWM outputs on P2/P3 using\n"+
                "//   Timer_B configured for up mode. The value in CCR0, 512-1, defines the PWM\n"+
                "//   period and the values in CCR1-6 the PWM duty cycles. Using ~1048kHz SMCLK\n"+
                "//   as TBCLK, the timer period is ~488us.\n"+
                "//   ACLK = 32kHz, SMCLK = MCLK = TBCLK = default DCO ~1048kHz.\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+

                "  P4SEL |= 0x7E;                            // P4 option select\n"+
                "  P4DIR |= 0x7E;                            // P4 outputs\n"+

                "  TBCCR0 = 512-1;                           // PWM Period\n"+
                "  TBCCTL1 = OUTMOD_7;                       // CCR1 reset/set\n"+
                "  TBCCR1 = 383;                             // CCR1 PWM Duty Cycle	\n"+
                "  TBCCTL2 = OUTMOD_7;                       // CCR2 reset/set\n"+
                "  TBCCR2 = 128;                             // CCR2 PWM duty cycle	\n"+
                "  TBCCTL3 = OUTMOD_7;                       // CCR3 reset/set\n"+
                "  TBCCR3 = 64;                              // CCR3 PWM duty cycle	\n"+
                "  TBCCTL4 = OUTMOD_7;                       // CCR4 reset/set\n"+
                "  TBCCR4 = 32;                              // CCR4 PWM duty cycle	\n"+
                "  TBCCTL5 = OUTMOD_7;                       // CCR5 reset/set\n"+
                "  TBCCR5 = 16;                              // CCR5 PWM duty cycle	\n"+
                "  TBCCTL6 = OUTMOD_7;                       // CCR6 reset/set\n"+
                "  TBCCR6 = 8;                               // CCR6 PWM duty cycle	\n"+
                "  TBCTL = TBSSEL_2 + MC_1 + TBCLR;          // SMCLK, upmode, clear TBR\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger \n"+
                "}\n";



        static String program59=   "//    MSP430F543xA Demo - USCI_A0, UART 9600 Full-Duplex Transceiver, 32K ACLK\n"+
                "// \n"+
                "//   Description: USCI_A0 communicates continuously as fast as possible\n"+
                "//   full-duplex with another device. Normal mode is LPM3, with activity only\n"+
                "//   during RX and TX ISR's. The TX ISR indicates the UART is ready to send\n"+
                "//   another character.  The RX ISR indicates the UART has received a character.\n"+
                "//   At 9600 baud, a full character is tranceived ~1ms.\n"+
                "//   The levels on P1.4/5 are TX'ed. RX'ed value is displayed on P1.0/1.\n"+
                "//   ACLK = BRCLK = LFXT1 = 32768, MCLK = SMCLK = DCO~ 1048k\n"+
                "//   Baud rate divider with 32768hz XTAL @9600 = 32768Hz/9600 = 3.41 (0003h 4Ah)\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+
                "  P7SEL |= 0x03;                            // Port select XT1\n"+

                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "    __delay_cycles(100000);                 // Delay for Osc to stabilize\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  P1OUT = 0x000;                            // P1.0/1 setup for LED output\n"+
                "  P1DIR |= BIT0+BIT1;                       //\n"+
                "  P3SEL |= BIT4+BIT5;                       // P3.4,5 UART option select\n"+

                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL1 |= UCSSEL_1;                     // CLK = ACLK\n"+
                "  UCA0BR0 = 0x03;                           // 32k/9600 - 3.41\n"+
                "  UCA0BR1 = 0x00;                           //\n"+
                "  UCA0MCTL = 0x06;                          // Modulation\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCTXIE + UCRXIE;                // Enable USCI_A0 TX/RX interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 w/ interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+


                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  unsigned char tx_char;\n"+

                "    switch(__even_in_range(UCA0IV,4))\n"+
                "  {\n"+
                "    case 0: break;                          // Vector 0 - no interrupt\n"+
                "    case 2:                                 // Vector 2 - RXIFG\n"+
                "      P1OUT = UCA0RXBUF;                    // RXBUF1 to TXBUF1\n"+
                "      break;\n"+
                "    case 4:                                 // Vector 4 - TXIFG\n"+
                "      __delay_cycles(5000);                 // Add small gap between TX'ed bytes\n"+
                "      tx_char = P1IN;\n"+
                "      tx_char = tx_char >> 4;\n"+
                "      UCA0TXBUF = tx_char;                  // Transmit character\n"+
                "      break;\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";


        static String program60=   "//   MSP430F543xA Demo - Software Toggle P1.0 at Default DCO\n"+
                "//\n"+
                "//   Description: Toggle P1.0 by xor'ing P1.0 inside of a software loop.\n"+
                "//   ACLK is rought out on pin P11.0, SMCLK is brought out on P11.2, and MCLK\n"+
                "//   is brought out on pin P11.1.\n"+
                "//   ACLK = REFO = 32.768kHz, MCLK = SMCLK = (Default DCO)/2 = (2MHz/2) = 1MHz\n"+
                "//\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  P1DIR |= BIT0;                            // P1.0 output\n"+
                "  P11DIR |= 0x07;                           // ACLK, MCLK, SMCLK set out to pins\n"+
                "  P11SEL |= 0x07;                           // P11.0,1,2 for debugging purposes.\n"+

                "  while(1)\n"+
                "  {\n"+
                "    P1OUT ^= BIT0;                          // Toggle P1.0\n"+
                "    __delay_cycles(60000);                  // Delay\n"+
                "  }\n"+
                "}\n";



        static String program61=    "//   MSP430F543xA Demo - Software Toggle P1.0 with 8MHz DCO\n"+
                "//\n"+
                "//   Description: Toggle P1.0 by xor'ing P1.0 inside of a software loop.\n"+
                "//   ACLK is rought out on pin P11.0, SMCLK is brought out on P11.2, and MCLK\n"+
                "//   is brought out on pin P11.1.\n"+
                "//   ACLK = REFO = 32kHz, MCLK = SMCLK = 8MHz\n"+
                "// \n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  P1DIR |= BIT0;                            // P1.0 output\n"+
                "  P11DIR |= 0x07;                           // ACLK, MCLK, SMCLK set out to pins\n"+
                "  P11SEL |= 0x07;                           // P11.0,1,2 for debugging purposes.\n"+

                "  UCSCTL3 |= SELREF_2;                      // Set DCO FLL reference = REFO\n"+
                "  UCSCTL4 |= SELA_2;                        // Set ACLK = REFO\n"+

                "  __bis_SR_register(SCG0);                  // Disable the FLL control loop\n"+
                "  UCSCTL0 = 0x0000;                         // Set lowest possible DCOx, MODx\n"+
                "  UCSCTL1 = DCORSEL_5;                      // Select DCO range 16MHz operation\n"+
                "  UCSCTL2 = FLLD_1 + 249;                   // Set DCO Multiplier for 8MHz\n"+
                "                                            // (N + 1) * FLLRef = Fdco\n"+
                "                                            // (249 + 1) * 32768 = 8MHz\n"+
                "                                            // Set FLL Div = fDCOCLK/2\n"+
                "  __bic_SR_register(SCG0);                  // Enable the FLL control loop\n"+

                "  // Worst-case settling time for the DCO when the DCO range bits have been\n"+
                "  // changed is n x 32 x 32 x f_MCLK / f_FLL_reference. See UCS chapter in 5xx\n"+
                "  // UG for optimization.\n"+
                "  // 32 x 32 x 8 MHz / 32,768 Hz = 250000 = MCLK cycles for DCO to settle\n"+
                "  __delay_cycles(250000);\n"+

                "  // Loop until XT1,XT2 & DCO fault flag is cleared\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  while(1)\n"+
                "  {\n"+
                "    P1OUT ^= BIT0;                          // Toggle P1.0\n"+
                "    __delay_cycles(600000);                 // Delay\n"+
                "  }\n"+
                "}\n";


        static String program62=    "//   MSP430F543xA Demo - Software Toggle P1.0 with 12MHz DCO\n"+
                "//\n"+
                "//   Description: Toggle P1.0 by xor'ing P1.0 inside of a software loop.\n"+
                "//   ACLK is rought out on pin P11.0, SMCLK is brought out on P11.2, and MCLK\n"+
                "//   is brought out on pin P11.1.\n"+
                "//   ACLK = REFO = 32kHz, MCLK = SMCLK = 12MHz\n"+
                "//   PMMCOREV = 1 to support up to 12MHz clock\n"+
                "//\n"+

                "//\n"+
                "//   Note: \n"+
                "//   In order to run the system at up to 12MHz, VCore must be set at 1.6V \n"+
                "//   or higher. \n"+

                "#include <msp430.h>\n"+

                "void SetVcoreUp (unsigned int level);\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop WDT\n"+
                "  SetVcoreUp (PMMCOREV_1);                  // Set VCore = 1.6V for 12MHz clock\n"+
                "  P1DIR |= BIT0;                            // P1.0 output\n"+
                "  P11DIR |= 0x07;                           // ACLK, MCLK, SMCLK set out to pins\n"+
                "  P11SEL |= 0x07;                           // P11.0,1,2 for debugging purposes.\n"+

                "  UCSCTL3 |= SELREF_2;                      // Set DCO FLL reference = REFO\n"+
                "  UCSCTL4 |= SELA_2;                        // Set ACLK = REFO\n"+

                "  __bis_SR_register(SCG0);                  // Disable the FLL control loop\n"+
                "  UCSCTL0 = 0x0000;                         // Set lowest possible DCOx, MODx\n"+
                "  UCSCTL1 = DCORSEL_5;                      // Select DCO range 24MHz operation\n"+
                "  UCSCTL2 = FLLD_1 + 374;                   // Set DCO Multiplier for 12MHz\n"+
                "                                            // (N + 1) * FLLRef = Fdco\n"+
                "                                            // (374 + 1) * 32768 = 12MHz\n"+
                "                                            // Set FLL Div = fDCOCLK/2\n"+
                "  __bic_SR_register(SCG0);                  // Enable the FLL control loop\n"+

                "  // Worst-case settling time for the DCO when the DCO range bits have been\n"+
                "  // changed is n x 32 x 32 x f_MCLK / f_FLL_reference. See UCS chapter in 5xx\n"+
                "  // UG for optimization.\n"+
                "  // 32 x 32 x 12 MHz / 32,768 Hz = 375000 = MCLK cycles for DCO to settle\n"+
                "  __delay_cycles(375000);\n"+

                "  // Loop until XT1,XT2 & DCO fault flag is cleared\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  while(1)\n"+
                "  {     \n"+
                "    P1OUT ^= BIT0;                          // Toggle P1.0\n"+
                "    __delay_cycles(600000);                 // Delay\n"+
                "  }\n"+
                "}\n"+

                "void SetVcoreUp (unsigned int level)\n"+
                "{\n"+
                "  // Open PMM registers for write\n"+
                "  PMMCTL0_H = PMMPW_H;              \n"+
                "  // Set SVS/SVM high side new level\n"+
                "  SVSMHCTL = SVSHE + SVSHRVL0 * level + SVMHE + SVSMHRRL0 * level;\n"+
                "  // Set SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Wait till SVM is settled\n"+
                "  while ((PMMIFG & SVSMLDLYIFG) == 0);\n"+
                "  // Clear already set flags\n"+
                "  PMMIFG &= ~(SVMLVLRIFG + SVMLIFG);\n"+
                "  // Set VCore to new level\n"+
                "  PMMCTL0_L = PMMCOREV0 * level;\n"+
                "  // Wait till new level reached\n"+
                "  if ((PMMIFG & SVMLIFG))\n"+
                "    while ((PMMIFG & SVMLVLRIFG) == 0);\n"+
                "  // Set SVS/SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVSLRVL0 * level + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Lock PMM registers for write access\n"+
                "  PMMCTL0_H = 0x00;\n"+
                "}\n";



        static String program63=  "//  MSP430F543xA Demo - FLL+, Runs Internal DCO at 2.45MHz with LFXT1 as Ref\n"+
                "//\n"+
                "//  Description: This program demonstrates setting the internal DCO to run at\n"+
                "//  2.45MHz with auto-calibration by the FLL+ circuitry. It uses LFXT1 as FLL\n"+
                "//  reference.\n"+
                "//  ACLK = LFXT1 = 32768Hz, MCLK = SMCLK = DCO = (74+1) x REFO = 2457600Hz	\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+

                "  P1DIR |= BIT0;                            // P1.0 output\n"+
                "  P11DIR |= 0x07;                           // ACLK, MCLK, SMCLK set out to pins\n"+
                "  P11SEL |= 0x07;                           // P11.0,1,2 for debugging purposes.\n"+

                "  // Initialize LFXT1\n"+
                "  P7SEL |= 0x03;                            // Select XT1\n"+
                "  UCSCTL6 &= ~(XT1OFF);                     // XT1 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  // Loop until XT1 fault flag is cleared\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~XT1LFOFFG;                  // Clear XT1 fault flags\n"+
                "  }while (UCSCTL7&XT1LFOFFG);               // Test XT1 fault flag\n"+

                "  // Initialize DCO to 2.45MHz\n"+
                "  __bis_SR_register(SCG0);                  // Disable the FLL control loop\n"+
                "  UCSCTL0 = 0x0000;                         // Set lowest possible DCOx, MODx\n"+
                "  UCSCTL1 = DCORSEL_3;                      // Set RSELx for DCO = 4.9 MHz\n"+
                "  UCSCTL2 = FLLD_1 + 74;                    // Set DCO Multiplier for 2.45MHz\n"+
                "                                            // (N + 1) * FLLRef = Fdco\n"+
                "                                            // (74 + 1) * 32768 = 2.45MHz\n"+
                "                                            // Set FLL Div = fDCOCLK/2\n"+
                "  __bic_SR_register(SCG0);                  // Enable the FLL control loop\n"+

                "  // Worst-case settling time for the DCO when the DCO range bits have been\n"+
                "  // changed is n x 32 x 32 x f_MCLK / f_FLL_reference. See UCS chapter in 5xx\n"+
                "  // UG for optimization.\n"+
                "  // 32 x 32 x 2.45 MHz / 32,768 Hz = 76563 = MCLK cycles for DCO to settle\n"+
                "  __delay_cycles(76563);\n"+

                "  // Loop until XT1,XT2 & DCO fault flag is cleared\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  while(1)\n"+
                "  {\n"+
                "    P1OUT ^= BIT0;                          // Toggle P1.0\n"+
                "    __delay_cycles(600000);                 // Delay\n"+
                "  }\n"+
                "}\n";



        static String program64=    "//  MSP430F543xA Demo - VLO sources ACLK. Toggles P1.0\n"+
                "//\n"+
                "//  Description: This program demonstrates using VLO to source ACLK\n"+
                "//  ACLK = VLO = ~12kHz	\n"+



                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_ADLY_250;                    // WDT ~1000ms, ACLK, interval timer\n"+
                "  SFRIE1 |= WDTIE;                          // Enable WDT interrupt\n"+

                "  P1DIR |= BIT0;                            // P1.0 to output direction\n"+
                "  P11DIR |= BIT0;                           // P11.0 to output direction\n"+
                "  P11SEL |= BIT0;                           // P11.0 to output ACLK\n"+

                "  UCSCTL4 |= SELA_1;                        // VLO Clock Sources ACLK\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 w/ interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Watchdog Timer interrupt service routine\n"+
                "#pragma vector = WDT_VECTOR\n"+
                "__interrupt void watchdog_timer(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0 using exclusive-OR\n"+
                "}\n";



        static String program65= "   //MSP430F543xA Demo - XT1 sources ACLK. Toggles P1.0\n"+
                "//\n"+
                "//Description: This program demonstrates using XT1 to source ACLK\n"+
                "//ACLK = LFXT1 = 32,768Hz	\n"+
                "//* An external watch crystal between XIN & XOUT is required for ACLK *//	\n"+



                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_ADLY_1000;                   // WDT 1000ms, ACLK, interval timer\n"+
                "  SFRIE1 |= WDTIE;                          // Enable WDT interrupt\n"+

                "  P1DIR |= BIT0;                            // P1.0 to output direction\n"+
                "  P11DIR |= BIT0;                           // P11.0 to output direction\n"+
                "  P11SEL |= BIT0;                           // P11.0 to output ACLK\n"+

                "  P7SEL |= 0x03;                            // Select XT1\n"+
                "  UCSCTL6 &= ~(XT1OFF);                     // XT1 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  // Loop until XT1,XT2 & DCO stabilizes\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  UCSCTL6 &= ~(XT1DRIVE_3);                 // Xtal is now stable, reduce drive\n"+
                "                                            // strength\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3 w/ interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Watchdog Timer interrupt service routine\n"+
                "#pragma vector = WDT_VECTOR\n"+
                "__interrupt void watchdog_timer(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0 using exclusive-OR\n"+
                "}\n";


        static String program66=  "//  MSP430F543xA Demo - FLL+, Output 32kHz Xtal + HF Xtal + Internal DCO\n"+
                "//\n"+
                "//  Description:  This program demonstrates using an external 32kHz crystal to\n"+
                "//  supply ACLK, and using a high speed crystal or resonator to supply SMCLK.\n"+
                "//  MLCK for the CPU is supplied by the internal DCO.  The 32kHz crystal\n"+
                "//  connects between pins Xin and Xout. The high frequency crystal or\n"+
                "//  resonator connects between pins XT2IN and XT2OUT.  The DCO clock is\n"+
                "//  generated internally and calibrated from the 32kHz crystal.  ACLK is\n"+
                "//  brought out on pin P11.0, SMCLK is brought out on P11.2, and MCLK is\n"+
                "//  brought out on pin P11.1.\n"+
                "//  ACLK = LFXT1 = 32768Hz, MCLK = default DCO = 32 x ACLK = 1048576Hz\n"+
                "//  SMCLK = HF XTAL\n"+
                "//  PMMCOREV = 2 to support up to 20MHz clock\n"+
                "//\n"+
                "//  NOTE: if the SMCLK/HF XTAL frequency exceeds 8MHz, VCore must be set\n"+
                "//  accordingly to support the system speed. Refer to MSP430x5xx Family User's Guide\n"+
                "//  Section 2.2 for more information.\n"+
                "//  //* An external watch crystal between XIN & XOUT is required for ACLK *//\n"+
                "//\n"+
                "//  NOTE: External matching capacitors must be added for the high\n"+
                "//       speed crystal or resonator as required.\n"+
                "//\n"+


                "#include <msp430.h>\n"+


                "void SetVcoreUp (unsigned int level);\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  SetVcoreUp(PMMCOREV_1);                     \n"+
                "  SetVcoreUp(PMMCOREV_2);                     // Set VCore to 1.8MHz for 20MHz\n"+

                "  P11DIR = BIT2 + BIT1 + BIT0;              // P11.2,1,0 to output direction\n"+
                "  P11SEL = BIT2 + BIT1 + BIT0;              // P11.2 to output SMCLK, P11.1\n"+
                // to output MCLK and P11.0 to\n"+
                // output ACLK\n"+
                "  P5SEL |= 0x0C;                            // Port select XT2\n"+
                "  P7SEL |= 0x03;                            // Port select XT1\n"+

                "  UCSCTL6 &= ~(XT1OFF + XT2OFF);            // Set XT1 & XT2 On\n"+
                "  UCSCTL6 |= XCAP_3;                        // Internal load cap\n"+

                "  // Loop until XT1,XT2 & DCO stabilizes\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  UCSCTL6 &= ~XT2DRIVE0;                    // Decrease XT2 Drive according to\n"+
                "                                            // expected frequency\n"+
                "  UCSCTL4 |= SELA_0 + SELS_5;               // Select SMCLK, ACLK source and DCO source\n"+

                "  while(1);                                 // Loop in place\n"+
                "}\n"+

                "void SetVcoreUp (unsigned int level)\n"+
                "{\n"+
                "  // Open PMM registers for write\n"+
                "  PMMCTL0_H = PMMPW_H;              \n"+
                "  // Set SVS/SVM high side new level\n"+
                "  SVSMHCTL = SVSHE + SVSHRVL0 * level + SVMHE + SVSMHRRL0 * level;\n"+
                "  // Set SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Wait till SVM is settled\n"+
                "  while ((PMMIFG & SVSMLDLYIFG) == 0);\n"+
                "  // Clear already set flags\n"+
                "  PMMIFG &= ~(SVMLVLRIFG + SVMLIFG);\n"+
                "  // Set VCore to new level\n"+
                "  PMMCTL0_L = PMMCOREV0 * level;\n"+
                "  // Wait till new level reached\n"+
                "  if ((PMMIFG & SVMLIFG))\n"+
                "    while ((PMMIFG & SVMLVLRIFG) == 0);\n"+
                "  // Set SVS/SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVSLRVL0 * level + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Lock PMM registers for write access\n"+
                "  PMMCTL0_H = 0x00;\n"+
                "}\n";



        static String program67=     "//  MSP430F543xA Demo - XT2 sources MCLK & SMCLK\n"+
                "//\n"+
                "//  Description: This program demonstrates using XT2 to source MCLK. XT1 is not\n"+
                "//  connected in this case.\n"+
                "//\n"+
                "//  By default, LFXT1 is requested by the following modules:\n"+
                "//     - FLL\n"+
                "//     - ACLK\n"+
                "//  If LFXT1 is NOT used and if the user does not change the source modules,\n"+
                "//  it causes the XT1xxOFIFG flag to be set because it is constantly looking\n"+
                "//  for LFXT1. OFIFG, global oscillator fault flag, will always be set if LFXT1\n"+
                "//  is set. Hence, it is important to ensure LFXT1 is no longer being sourced\n"+
                "//  if LFXT1 is NOT used.\n"+
                "//  MCLK = XT2\n"+
                "//  PMMCOREV = 2 to support up to 20MHz clock\n"+
                "//\n"+
                "//  NOTE: if the SMCLK/HF XTAL frequency exceeds 8MHz, VCore must be set\n"+
                "//  accordingly to support the system speed. Refer to MSP430x5xx Family User's Guide\n"+
                "//  Section 2.2 for more information.\n"+

                "//   Note: \n"+
                "//   In order to run the system at up to 20MHz, VCore must be set at 1.8V \n"+
                "//   or higher. This is done by invoking function SetVcoreUp().\n"+


                "#include <msp430.h>\n"+

                "void SetVcoreUp (unsigned int level);\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer\n"+
                "  SetVcoreUp(PMMCOREV_1); \n"+
                "  SetVcoreUp(PMMCOREV_2);                     // Set VCore to 1.8MHz for 20MHz\n"+

                "  P11DIR = BIT1+BIT2;                       // P11.1-2 to output direction\n"+
                "  P11SEL |= BIT1+BIT2;                      // P11.1-2 to output SMCLK,MCLK\n"+
                "  P5SEL |= 0x0C;                            // Port select XT2\n"+

                "  UCSCTL6 &= ~XT2OFF;                       // Enable XT2\n"+
                "  UCSCTL3 |= SELREF_2;                      // FLLref = REFO\n"+
                "                                            // Since LFXT1 is not used,\n"+
                "                                            // sourcing FLL with LFXT1 can cause\n"+
                "                                            // XT1OFFG flag to set\n"+
                "  UCSCTL4 |= SELA_2;                        // ACLK=REFO,SMCLK=DCO,MCLK=DCO\n"+

                "  // Loop until XT1,XT2 & DCO stabilizes\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  UCSCTL6 &= ~XT2DRIVE0;                    // Decrease XT2 Drive according to\n"+
                "                                            // expected frequency\n"+
                "  UCSCTL4 |= SELS_5 + SELM_5;               // SMCLK=MCLK=XT2\n"+

                "  while(1);                                 // Loop in place\n"+
                "}\n"+

                "void SetVcoreUp (unsigned int level)\n"+
                "{\n"+
                "  // Open PMM registers for write\n"+
                "  PMMCTL0_H = PMMPW_H;              \n"+
                "  // Set SVS/SVM high side new level\n"+
                "  SVSMHCTL = SVSHE + SVSHRVL0 * level + SVMHE + SVSMHRRL0 * level;\n"+
                "  // Set SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Wait till SVM is settled\n"+
                "  while ((PMMIFG & SVSMLDLYIFG) == 0);\n"+
                "  // Clear already set flags\n"+
                "  PMMIFG &= ~(SVMLVLRIFG + SVMLIFG);\n"+
                "  // Set VCore to new level\n"+
                "  PMMCTL0_L = PMMCOREV0 * level;\n"+
                "  // Wait till new level reached\n"+
                "  if ((PMMIFG & SVMLIFG))\n"+
                "    while ((PMMIFG & SVMLVLRIFG) == 0);\n"+
                "  // Set SVS/SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVSLRVL0 * level + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Lock PMM registers for write access\n"+
                "  PMMCTL0_H = 0x00;\n"+
                "}\n";



        static String program68=     "{//  MSP430F543xA Demo - LFXT1 HF Xtal + Internal DCO\n"+
                "//\n"+
                "//  Description:  This program demonstrates using an external high speed crystal\n"+
                "//  or resonator to supply ACLK and MCLK for the CPU. SMLCK is supplied by the\n"+
                "//  internal DCO.  The high speed crystal or resonator connects between pins\n"+
                "//  Xin and Xout. The DCO clock is generated internally and calibrated from REFO\n"+
                "//  ACLK is brought out on pin P11.0, SMCLK is brought out on P11.2, and MCLK is\n"+
                "//  brought out on pin P11.1.\n"+
                "//  ACLK = LFXT1 = HF XTAL, MCLK = HF XTAL, SMCLK = 32 x ACLK = 1048576Hz\n"+
                "//  PMMCOREV = 2 to support up to 20MHz clock\n"+
                "//\n"+
                "//  NOTE: if the SMCLK/HF XTAL frequency exceeds 8MHz, VCore must be set\n"+
                "//  accordingly to support the system speed. Refer to MSP430x5xx Family User's Guide\n"+
                "//  Section 2.2 for more information.\n"+
                "//  NOTE: External matching capacitors must be added for the high\n"+
                "//       speed crystal or resonator as required.\n"+

                "//   Note: \n"+
                "//   In order to run the system at up to 20MHz, VCore must be set at 1.8V \n"+
                "//   or higher. This is done by invoking function SetVcoreUp().\n"+


                "#include <msp430.h>\n"+

                "void SetVcoreUp (unsigned int level);\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  SetVcoreUp(PMMCOREV_1); \n"+
                "  SetVcoreUp(PMMCOREV_2);                     // Set VCore to 1.8MHz for 20MHz\n"+

                "  P11DIR = BIT2 + BIT1 + BIT0;              // P11.2,1,0 to output direction\n"+
                "  P11SEL = BIT2 + BIT1 + BIT0;              // P11.2 to output SMCLK, P11.1\n"+
                "                                            // to output MCLK and P11.0 to\n"+
                "                                            // output ACLK\n"+
                "  P7SEL |= 0x03;                            // Port select XT1\n"+
                "  UCSCTL3 |= SELREF_2;                      // FLL Ref = REFO\n"+
                "  UCSCTL6 &= ~XT1OFF;                       // Set XT1 On\n"+
                "  UCSCTL6 |= XT1DRIVE_3 + XTS;              // Max drive strength, adjust\n"+
                "                                            // according to crystal frequency.\n"+
                "                                            // LFXT1 HF mode\n"+

                "  // Loop until XT1,XT2 & DCO stabilizes\n"+
                "  do\n"+
                "  {\n"+
                "    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + XT1HFOFFG + DCOFFG);\n"+
                "                                            // Clear XT2,XT1,DCO fault flags\n"+
                "    SFRIFG1 &= ~OFIFG;                      // Clear fault flags\n"+
                "  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag\n"+

                "  UCSCTL4 = SELA_0 + SELS_4 + SELM_0;       // Select ACLK = LFXT1\n"+
                "                                            //       SMCLK = DCO\n"+
                "                                            //        MCLK = LFXT1\n"+
                "  while(1);                                 // Loop in place\n"+
                "}\n"+

                "void SetVcoreUp (unsigned int level)\n"+
                "{\n"+
                "  // Open PMM registers for write\n"+
                "  PMMCTL0_H = PMMPW_H;              \n"+
                "  // Set SVS/SVM high side new level\n"+
                "  SVSMHCTL = SVSHE + SVSHRVL0 * level + SVMHE + SVSMHRRL0 * level;\n"+
                "  // Set SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Wait till SVM is settled\n"+
                "  while ((PMMIFG & SVSMLDLYIFG) == 0);\n"+
                "  // Clear already set flags\n"+
                "  PMMIFG &= ~(SVMLVLRIFG + SVMLIFG);\n"+
                "  // Set VCore to new level\n"+
                "  PMMCTL0_L = PMMCOREV0 * level;\n"+
                "  // Wait till new level reached\n"+
                "  if ((PMMIFG & SVMLIFG))\n"+
                "    while ((PMMIFG & SVMLVLRIFG) == 0);\n"+
                "  // Set SVS/SVM low side to new level\n"+
                "  SVSMLCTL = SVSLE + SVSLRVL0 * level + SVMLE + SVSMLRRL0 * level;\n"+
                "  // Lock PMM registers for write access\n"+
                "  PMMCTL0_H = 0x00;\n"+
                "}\n";



        static String program69=  "//   MSP430F543xA Demo - USCI_A0, SPI 3-Wire Master Incremented Data\n"+
                "//\n"+
                "//   Description: SPI master talks to SPI slave using 3-wire mode. Incrementing\n"+
                "//   data is sent by the master starting at 0x01. Received data is expected to\n"+
                "//   be same as the previous transmission.  USCI RX ISR is used to handle\n"+
                "//   communication with the CPU, normally in LPM0. If high, P1.0 indicates\n"+
                "//   valid data reception.  Because all execution after LPM0 is in ISRs,\n"+
                "//   initialization waits for DCO to stabilize against ACLK.\n"+
                "//   ACLK = ~32.768kHz, MCLK = SMCLK = DCO ~ 1048kHz.  BRCLK = SMCLK/2\n"+
                "//\n"+
                "//   Use with SPI Slave Data Echo code example.  If slave is in debug mode, P1.1\n"+
                "//   slave reset signal conflicts with slave's JTAG; to work around, use IAR's\n"+
                "//   'Release JTAG on Go' on slave device.  If breakpoints are set in\n"+
                "//   slave RX ISR, master must stopped also to avoid overrunning slave\n"+
                "//   RXBUF.\n"+


                "#include <msp430.h>\n"+

                "unsigned char MST_Data,SLV_Data;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  P1OUT |= 0x02;                            // Set P1.0 for LED\n"+
                "                                            // Set P1.1 for slave reset\n"+
                "  P1DIR |= 0x03;                            // Set P1.0-2 to output direction\n"+
                "  P3SEL |= 0x31;                            // P3.5,4,0 option select\n"+

                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL0 |= UCMST+UCSYNC+UCCKPL+UCMSB;    // 3-pin, 8-bit SPI master\n"+
                "                                            // Clock polarity high, MSB\n"+
                "  UCA0CTL1 |= UCSSEL_2;                     // SMCLK\n"+
                "  UCA0BR0 = 0x02;                           // /2\n"+
                "  UCA0BR1 = 0;                              //\n"+
                "  UCA0MCTL = 0;                             // No modulation\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A0 RX interrupt\n"+

                "  P1OUT &= ~0x02;                           // Now with SPI signals initialized,\n"+
                "  P1OUT |= 0x02;                            // reset slave\n"+

                "  __delay_cycles(100);                      // Wait for slave to initialize\n"+

                "  MST_Data = 0x01;                          // Initialize data values\n"+
                "  SLV_Data = 0x00;                          //\n"+

                "  while (!(UCA0IFG&UCTXIFG));               // USCI_A0 TX buffer ready?\n"+
                "  UCA0TXBUF = MST_Data;                     // Transmit first character\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // CPU off, enable interrupts\n"+
                "}\n"+

                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                " {\n"+
                "    case 0: break;                          // Vector 0 - no interrupt\n"+
                "    case 2:                                 // Vector 2 - RXIFG\n"+
                "      while (!(UCA0IFG&UCTXIFG));           // USCI_A0 TX buffer ready?\n"+

                "      if (UCA0RXBUF==SLV_Data)              // Test for correct character RX'd\n"+
                "        P1OUT |= 0x01;                      // If correct, light LED\n"+
                "      else\n"+
                "        P1OUT &= ~0x01;                     // If incorrect, clear LED\n"+

                "      MST_Data++;                           // Increment data\n"+
                "      SLV_Data++;\n"+
                "      UCA0TXBUF = MST_Data;                 // Send next value\n"+

                "      __delay_cycles(40);                   // Add time between transmissions to\n"+
                "                                            // make sure slave can process information\n"+
                "      break;\n"+
                "    case 4: break;                          // Vector 4 - TXIFG\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";


        static String program70=   "//   MSP430F543xA Demo - USCI_A0, SPI 3-Wire Slave Data Echo\n"+
                "//\n"+
                "//   Description: SPI slave talks to SPI master using 3-wire mode. Data received\n"+
                "//   from master is echoed back.  USCI RX ISR is used to handle communication,\n"+
                "//   CPU normally in LPM4.  Prior to initial data exchange, master pulses\n"+
                "//   slaves RST for complete reset.\n"+
                "//   ACLK = ~32.768kHz, MCLK = SMCLK = DCO ~ 1048kHz\n"+
                "//\n"+
                "//   Use with SPI Master Incremented Data code example.  If the slave is in\n"+
                "//   debug mode, the reset signal from the master will conflict with slave's\n"+
                "//   JTAG; to work around, use IAR's 'Release JTAG on Go' on slave device.  If\n"+
                "//   breakpoints are set in slave RX ISR, master must stopped also to avoid\n"+
                "//   overrunning slave RXBUF.\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW+WDTHOLD;                   // Stop watchdog timer\n"+

                "  while(!(P3IN&0x01));                      // If clock sig from mstr stays low,\n"+
                // it is not yet in SPI mode\n"+
                "  P3SEL |= 0x31;                            // P3.5,4,0 option select\n"+
                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL0 |= UCSYNC+UCCKPL+UCMSB;          // 3-pin, 8-bit SPI slave,\n"+
                // Clock polarity high, MSB\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A0 RX interrupt\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4, enable interrupts\n"+
                "}\n"+

                "// Echo character\n"+
                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                "  {\n"+
                "    case 0:break;                             // Vector 0 - no interrupt\n"+
                "    case 2:                                   // Vector 2 - RXIFG\n"+
                "      while (!(UCA0IFG&UCTXIFG));             // USCI_A0 TX buffer ready?\n"+
                "      UCA0TXBUF = UCA0RXBUF;\n"+
                "      break;\n"+
                "    case 4:break;                             // Vector 4 - TXIFG\n"+
                "    default: break;\n"+
                "  }\n"+
                "}\n";



        static String program71=   "//   MSP430F543xA Demo - USCI_A0, 115200 UART Echo ISR, DCO SMCLK\n"+
                "//\n"+
                "//   Description: Echo a received character, RX ISR used. Normal mode is LPM0.\n"+
                "//   USCI_A0 RX interrupt triggers TX Echo.\n"+
                "//   Baud rate divider with 1048576hz = 1048576/115200 = ~9.1 (009h|01h)\n"+
                "//   ACLK = REFO = ~32768Hz, MCLK = SMCLK = default DCO = 32 x ACLK = 1048576Hz\n"+
                "//   See User Guide for baud rate divider table\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+

                "  P3SEL = 0x30;                             // P3.4,5 = USCI_A0 TXD/RXD\n"+
                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL1 |= UCSSEL_2;                     // SMCLK\n"+
                "  UCA0BR0 = 9;                              // 1MHz 115200 (see User's Guide)\n"+
                "  UCA0BR1 = 0;                              // 1MHz 115200\n"+
                "  UCA0MCTL |= UCBRS_1 + UCBRF_0;            // Modulation UCBRSx=1, UCBRFx=0\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A0 RX interrupt\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Echo back RXed character, confirm TX buffer is ready first\n"+
                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                "  {\n"+
                "  case 0:break;                             // Vector 0 - no interrupt\n"+
                "  case 2:                                   // Vector 2 - RXIFG\n"+
                "    while (!(UCA0IFG&UCTXIFG));             // USCI_A0 TX buffer ready?\n"+
                "    UCA0TXBUF = UCA0RXBUF;                  // TX -> RXed character\n"+
                "    break;\n"+
                "  case 4:break;                             // Vector 4 - TXIFG\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";


        static String program72=    "//   MSP430F543xA Demo - USCI_A0, Ultra-Low Pwr UART 2400 Echo ISR, 32kHz ACLK\n"+
                "//\n"+
                "//   Description: Echo a received character, RX ISR used. Normal mode is LPM3,\n"+
                "//   USCI_A0 RX interrupt triggers TX Echo.\n"+
                "//   ACLK = REFO = ~32768Hz, MCLK = SMCLK = DCO ~1.045MHz\n"+
                "//   Baud rate divider with 32768Hz XTAL @2400 -- from User's Guide\n"+
                "//   See User Guide for baud rate divider table\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL = 0x30;                             // P3.4,5 = USCI_A0 TXD/RXD\n"+

                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL1 |= UCSSEL_1;                     // CLK = ACLK\n"+
                "  UCA0BR0 = 0x0D;                           // 2400 (see User's Guide)\n"+
                "  UCA0BR1 = 0x00;                           //\n"+
                "  UCA0MCTL |= UCBRS_6+UCBRF_0;              // Modulation UCBRSx=6, UCBRFx=0\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A1 RX interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Echo back RXed character, confirm TX buffer is ready first\n"+
                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                "  {\n"+
                "  case 0:break;                             // Vector 0 - no interrupt\n"+
                "  case 2:                                   // Vector 2 - RXIFG\n"+
                "    while (!(UCA0IFG&UCTXIFG));             // USCI_A1 TX buffer ready?\n"+
                "    UCA0TXBUF = UCA0RXBUF;                  // TX -> RXed character\n"+
                "    break;\n"+
                "  case 4:break;                             // Vector 4 - TXIFG\n"+
                "  default: break;\n"+
                "  }  \n"+
                "}\n";



        static String program73=   "//   MSP430F543xA Demo - USCI_A0, Ultra-Low Pwr UART 9600 Echo ISR, 32kHz ACLK\n"+
                "//\n"+
                "//   Description: Echo a received character, RX ISR used. Normal mode is LPM3,\n"+
                "//   USCI_A0 RX interrupt triggers TX Echo.\n"+
                "//   ACLK = REFO = 32768Hz, MCLK = SMCLK = DCO ~1.045MHz\n"+
                "//   Baud rate divider with 32768Hz XTAL @9600 = 32768Hz/9600 = 3.41\n"+
                "//   See User Guide for baud rate divider table\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL = 0x30;                             // P3.4,5 = USCI_A0 TXD/RXD\n"+
                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL1 |= UCSSEL_1;                     // CLK = ACLK\n"+
                "  UCA0BR0 = 0x03;                           // 32kHz/9600=3.41 (see User's Guide)\n"+
                "  UCA0BR1 = 0x00;                           //\n"+
                "  UCA0MCTL = UCBRS_3+UCBRF_0;               // Modulation UCBRSx=3, UCBRFx=0\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A0 RX interrupt\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Echo back RXed character, confirm TX buffer is ready first\n"+
                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                "  {\n"+
                "  case 0:break;                             // Vector 0 - no interrupt\n"+
                "  case 2:                                   // Vector 2 - RXIFG\n"+
                "    while (!(UCA0IFG&UCTXIFG));             // USCI_A0 TX buffer ready?\n"+
                "    UCA0TXBUF = UCA0RXBUF;                  // TX -> RXed character\n"+
                "    break;\n"+
                "  case 4:break;                             // Vector 4 - TXIFG\n"+
                "  default: break;  \n"+
                "  }\n"+
                "}\n";


        static String program74=    "//   MSP430F5438A Demo - USCI_A0, 9600 UART, SMCLK, LPM0, Echo with over-sampling\n"+
                "//\n"+
                "//   Description: Echo a received character, RX ISR used. Normal mode is LPM0.\n"+
                "//   USCI_A0 RX interrupt triggers TX Echo.\n"+
                "//   If UCOS16=1, UCBRx=Fbrclk/(16*Baudrate)\n"+
                "//   Baud rate divider with UCBRx = 1MHz/(16*9600) = ~6.8\n"+
                "//   ACLK = REFO = ~32768Hz, MCLK = SMCLK = default DCO = 32 x ACLK = 1048576Hz\n"+
                "//   See User Guide for baud rate divider table\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL = 0x30;                             // P3.4,5 = USCI_A0 TXD/RXD\n"+
                "  UCA0CTL1 |= UCSWRST;                      // **Put state machine in reset**\n"+
                "  UCA0CTL1 |= UCSSEL_2;                     // SMCLK\n"+
                "  UCA0BR0 = 6;                              // 1MHz 9600 (see User's Guide)\n"+
                "  UCA0BR1 = 0;                              // 1MHz 9600\n"+
                "  UCA0MCTL = UCBRS_0 + UCBRF_13 + UCOS16;   // Modln UCBRSx=0, UCBRFx=0,\n"+
                "                                            // over sampling\n"+
                "  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**\n"+
                "  UCA0IE |= UCRXIE;                         // Enable USCI_A0 RX interrupt\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, interrupts enabled\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Echo back RXed character, confirm TX buffer is ready first\n"+
                "#pragma vector=USCI_A0_VECTOR\n"+
                "__interrupt void USCI_A0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCA0IV,4))\n"+
                "{\n"+
                "  case 0:break;                             // Vector 0 - no interrupt\n"+
                "  case 2:                                   // Vector 2 - RXIFG\n"+
                "    while (!(UCA0IFG&UCTXIFG));             // USCI_A0 TX buffer ready?\n"+
                "    UCA0TXBUF = UCA0RXBUF;                  // TX -> RXed character\n"+
                "    break;\n"+
                "  case 4:break;                             // Vector 4 - TXIFG\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";


        static String program75=   "//  MSP430F543xA Demo - USCI_B0 I2C Master RX single bytes from MSP430 Slave\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  reads from the slave. This is the MASTER CODE. The data from the slave\n"+
                "//  transmitter begins at 0 and increments with each transfer. The received\n"+
                "//  data is in R5 and is checked for validity. If the received data is\n"+
                "//  incorrect, the CPU is trapped and the P1.0 LED will stay on. The USCI_B0\n"+
                "//  RX interrupt is used to know when new data has been received.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = BRCLK = default DCO = ~1.045MHz\n"+
                "//\n"+
                "//      ***to be used with msp430x54xA_uscib0_i2c_05.c***\n"+
                "//\n"+


                "#include <msp430.h>\n"+

                "unsigned char RXData;\n"+
                "unsigned char RXCompare;\n"+

                "int main(void)\n"+
                "{\n"+
                "WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P1OUT &= ~0x01;                           // P1.0 = 0\n"+
                "  P1DIR |= 0x01;                            // P1.0 output\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMST + UCMODE_3 + UCSYNC;     // I2C Master, synchronous mode\n"+
                "  UCB0CTL1 = UCSSEL_2 + UCSWRST;            // Use SMCLK\n"+
                "  UCB0BR0 = 12;                             // fSCL = SMCLK/12 = ~100kHz\n"+
                "  UCB0BR1 = 0;\n"+
                "  UCB0I2CSA = 0x48;                        // Slave Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCRXIE;                         // Enable RX interrupt\n"+
                "  RXCompare = 0x0;                          // Used to check incoming data\n"+

                "  while (1)\n"+
                "  {\n"+
                "    while (UCB0CTL1 & UCTXSTP);             // Ensure stop condition got sent\n"+
                "    UCB0CTL1 |= UCTXSTT;                    // I2C start condition\n"+
                "    while(UCB0CTL1 & UCTXSTT);              // Start condition sent?\n"+
                "    UCB0CTL1 |= UCTXSTP;                    // I2C stop condition\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "    __no_operation();                       // For debugger\n"+

                "    if (RXData != RXCompare)                // Trap CPU if wrong\n"+
                "    {\n"+
                "      P1OUT |= 0x01;                        // P1.0 = 1\n"+
                "    }\n"+

                "    RXCompare++;                            // Increment correct RX value\n"+
                "  }\n"+
                "}\n"+

                "// USCI_B0 Data ISR\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6: break;                           // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "    RXData = UCB0RXBUF;                     // Get RX data\n"+
                "    __bic_SR_register_on_exit(LPM0_bits);   // Exit active CPU\n"+
                "    break;\n"+
                "  case 12: break;                           // Vector 12: TXIFG\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";


        static String program76=    "//  MSP430F543xA Demo - USCI_B0 I2C Slave TX single bytes to MSP430 Master\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  reads from the slave. This is the SLAVE code. The TX data begins at 0\n"+
                "//  and is incremented each time it is sent. An incoming start condition\n"+
                "//  is used as a trigger to increment the outgoing data. The master checks the\n"+
                "//  data it receives for validity. If it is incorrect, it stops communicating\n"+
                "//  and the P1.0 LED will stay on. The USCI_B0 TX interrupt is used to know\n"+
                "//  when to TX.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO = ~1.045MHz\n"+
                "//\n"+
                "//    ***to be used with msp430x54xA_uscib0_i2c_04.c***\n"+


                "#include <msp430.h>\n"+

                "unsigned char TXData;\n"+
                "unsigned char i=0;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+

                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMODE_3 + UCSYNC;             // I2C Slave, synchronous mode\n"+
                "  UCB0I2COA = 0x48;                         // Own Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCTXIE + UCSTTIE + UCSTPIE;     // Enable TX interrupt\n"+
                "                                            // Enable Start condition interrupt\n"+
                "  TXData = 0;                               // Used to hold TX data\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0 w/ interrupts  \n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// USCI_B0 State ISR\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6:                                  // Vector  6: STTIFG\n"+
                "     UCB0IFG &= ~UCSTTIFG;                  // Clear start condition int flag\n"+
                "     break;\n"+
                "  case  8:                                  // Vector  8: STPIFG\n"+
                "    TXData++;                               // Increment TXData\n"+
                "    UCB0IFG &= ~UCSTPIFG;                   // Clear stop condition int flag\n"+
                "    break;\n"+
                "  case 10: break;                           // Vector 10: RXIFG  \n"+
                "  case 12:                                  // Vector 12: TXIFG\n"+
                "    UCB0TXBUF = TXData;                     // TX data\n"+
                "    break;\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";



        static String program77=   "//  MSP430F543xA Demo - USCI_B0 I2C Master TX single bytes to MSP430 Slave\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  transmits to the slave. This is the master code. It continuously\n"+
                "//  transmits 00h, 01h, ..., 0ffh and demonstrates how to implement an I2C\n"+
                "//  master transmitter sending a single byte using the USCI_B0 TX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = BRCLK = default DCO = ~1.045MHz\n"+



                "#include <msp430.h>\n"+

                "unsigned char TXData;\n"+
                "unsigned char TXByteCtr;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMST + UCMODE_3 + UCSYNC;     // I2C Master, synchronous mode\n"+
                "  UCB0CTL1 = UCSSEL_2 + UCSWRST;            // Use SMCLK, keep SW reset\n"+
                "  UCB0BR0 = 12;                             // fSCL = SMCLK/12 = ~100kHz\n"+
                "  UCB0BR1 = 0;  \n"+
                "  UCB0I2CSA = 0x48;                         // Slave Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCTXIE;                         // Enable TX interrupt\n"+

                "  TXData = 0x01;                            // Holds TX data\n"+

                "  while (1)\n"+
                "  {\n"+
                "    TXByteCtr = 1;                          // Load TX byte counter\n"+

                "    while (UCB0CTL1 & UCTXSTP);             // Ensure stop condition got sent\n"+
                "    UCB0CTL1 |= UCTR + UCTXSTT;             // I2C TX, start condition\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0 w/ interrupts\n"+
                "    __no_operation();                       // Remain in LPM0 until all data\n"+
                "                                            // is TX'd\n"+

                "    TXData++;                               // Increment data byte\n"+
                "  }\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCIAB0_ISR is structured such that it can be used to transmit any\n"+
                "// number of bytes by pre-loading TXByteCtr with the byte count.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6: break;                           // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10: break;                           // Vector 10: RXIFG\n"+
                "  case 12:                                  // Vector 12: TXIFG  \n"+
                "    if (TXByteCtr)                          // Check TX byte counter\n"+
                "    {\n"+
                "      UCB0TXBUF = TXData;                   // Load TX buffer\n"+
                "      TXByteCtr--;                          // Decrement TX byte counter\n"+
                "    }\n"+
                "    else\n"+
                "    {\n"+
                "      UCB0CTL1 |= UCTXSTP;                  // I2C stop condition\n"+
                "      UCB0IFG &= ~UCTXIFG;                  // Clear USCI_B0 TX int flag\n"+
                "      __bic_SR_register_on_exit(LPM0_bits); // Exit LPM0\n"+
                "    }\n"+
                "    break;\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";


        static String program78=   "//  MSP430F543xA Demo - USCI_B0 I2C Slave RX single bytes from MSP430 Master\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  transmits to the slave. This is the slave code. The interrupt driven\n"+
                "//  data receiption is demonstrated using the USCI_B0 RX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO = ~1.045MHz\n"+



                "#include <msp430.h>\n"+

                "volatile unsigned char RXData;\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMODE_3 + UCSYNC;             // I2C Slave, synchronous mode\n"+
                "  UCB0I2COA = 0x48;                         // Own Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCRXIE;                         // Enable RX interrupt\n"+

                "  while (1)\n"+
                "  {\n"+
                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "    __no_operation();                       // Set breakpoint >>here<< and read\n"+
                "  }                                         // RXData\n"+
                "}\n"+

                "// USCI_B0 Data ISR\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6: break;                           // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "    RXData = UCB0RXBUF;                     // Get RX data\n"+
                "    __bic_SR_register_on_exit(LPM0_bits);   // Exit LPM0\n"+
                "    break;\n"+
                "  case 12: break;                           // Vector 12: TXIFG  \n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";



        static String program79=   "//  MSP430F543xA Demo - USCI_B0 I2C Master TX multiple bytes to MSP430 Slave\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master  \n"+
                "//  transmits to the slave. This is the MASTER CODE. It cntinuously\n"+
                "//  transmits an array of data and demonstrates how to implement an I2C\n"+
                "//  master transmitter sending multiple bytes using the USCI_B0 TX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = BRCLK = default DCO = ~1.045MHz\n"+

                "//\n"+
                "//      ***to be used with msp430x54xA_uscib0_i2c_09.c***\n"+


                "#include <msp430.h>\n"+

                "unsigned char *PTxData;                     // Pointer to TX data\n"+
                "unsigned char TXByteCtr;\n"+

                "const unsigned char TxData[] =              // Table of data to transmit\n"+
                "{\n"+
                "  0x11,\n"+
                "  0x22,\n"+
                "  0x33,\n"+
                "  0x44,\n"+
                "  0x55\n"+
                "};\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMST + UCMODE_3 + UCSYNC;     // I2C Master, synchronous mode\n"+
                "  UCB0CTL1 = UCSSEL_2 + UCSWRST;            // Use SMCLK, keep SW reset\n"+
                "  UCB0BR0 = 12;                             // fSCL = SMCLK/12 = ~100kHz\n"+
                "  UCB0BR1 = 0;\n"+
                "  UCB0I2CSA = 0x48;                         // Slave Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCTXIE;                         // Enable TX interrupt\n"+

                "  while (1)\n"+
                "  {\n"+
                "    __delay_cycles(50);                     // Delay required between transaction\n"+
                "    PTxData = (unsigned char *)TxData;      // TX array start address\n"+
                "                                            // Place breakpoint here to see each\n"+
                "                                            // transmit operation.\n"+
                "    TXByteCtr = sizeof TxData;              // Load TX byte counter\n"+

                "    UCB0CTL1 |= UCTR + UCTXSTT;             // I2C TX, start condition\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "    __no_operation();                       // Remain in LPM0 until all data\n"+
                "                                            // is TX'd\n"+
                "    while (UCB0CTL1 & UCTXSTP);             // Ensure stop condition got sent\n"+
                "  }\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCIAB0TX_ISR is structured such that it can be used to transmit any\n"+
                "// number of bytes by pre-loading TXByteCtr with the byte count. Also, TXData\n"+
                "// points to the next byte to transmit.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6: break;                           // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10: break;                           // Vector 10: RXIFG\n"+
                "  case 12:                                  // Vector 12: TXIFG\n"+
                "    if (TXByteCtr)                          // Check TX byte counter\n"+
                "    {\n"+
                "      UCB0TXBUF = *PTxData++;               // Load TX buffer\n"+
                "      TXByteCtr--;                          // Decrement TX byte counter\n"+
                "    }\n"+
                "    else\n"+
                "    {\n"+
                "      UCB0CTL1 |= UCTXSTP;                  // I2C stop condition\n"+
                "      UCB0IFG &= ~UCTXIFG;                  // Clear USCI_B0 TX int flag\n"+
                "      __bic_SR_register_on_exit(LPM0_bits); // Exit LPM0\n"+
                "    }\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";


        static String program80=   "//  MSP430F543xA Demo - USCI_B0 I2C Slave RX multiple bytes from MSP430 Master\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  transmits to the slave. This is the slave code. The interrupt driven\n"+
                "//  data receiption is demonstrated using the USCI_B0 RX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO = ~1.045MHz\n"+
                "//\n"+




                "#include <msp430.h>\n"+

                "unsigned char *PRxData;                     // Pointer to RX data\n"+
                "unsigned char RXByteCtr;\n"+
                "volatile unsigned char RxBuffer[128];       // Allocate 128 byte of RAM\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+

                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMODE_3 + UCSYNC;             // I2C Slave, synchronous mode\n"+
                "  UCB0I2COA = 0x48;                         // Own Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCSTPIE + UCSTTIE + UCRXIE;     // Enable STT, STP & RX interrupt\n"+

                "  while (1)\n"+
                "  {\n"+
                "    PRxData = (unsigned char *)RxBuffer;    // Start of RX buffer\n"+
                "    RXByteCtr = 0;                          // Clear RX byte count\n"+
                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "                                            // Remain in LPM0 until master\n"+
                "                                            // finishes TX\n"+
                "    __no_operation();                       // Set breakpoint >>here<< and read\n"+
                "  }                                         // read out the RxData buffer\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 data ISR RX vector is used to move received data from the I2C\n"+
                "// master to the MSP430 memory.\n"+
                "//------------------------------------------------------------------------------\n"+
                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 state ISR TX vector is used to wake up the CPU from LPM0 in order\n"+
                "// to process the received data in the main program. LPM0 is only exit in case\n"+
                "// of a (re-)start or stop condition when actual data was received.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6:                                  // Vector  6: STTIFG\n"+
                "    UCB0IFG &= ~UCSTTIFG;\n"+
                "    break;\n"+
                "  case  8:                                  // Vector  8: STPIFG\n"+
                "    UCB0IFG &= ~UCSTPIFG;\n"+
                "    if (RXByteCtr)                          // Check RX byte counter\n"+
                "      __bic_SR_register_on_exit(LPM0_bits);\n"+
                "    break;\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "    *PRxData++ = UCB0RXBUF;                 // Get RX'd byte into buffer\n"+
                "    RXByteCtr++;\n"+
                "    break;\n"+
                "  case 12: break;                           // Vector 12: TXIFG  \n"+
                "  default: break;\n"+
                "  }  \n"+
                "}\n";



        static String program81=   "//  MSP430F543xA Demo - USCI_B0 I2C Master RX multiple bytes from MSP430 Slave\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The slave\n"+
                "//  transmits to the master. This is the MASTER CODE. It continuously\n"+
                "//  receives an array of data and demonstrates how to implement an I2C\n"+
                "//  master receiver receiving multiple bytes using the USCI_B0 TX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = BRCLK = default DCO = ~1.045MHz\n"+
                "//\n"+
                "//      ***to be used with msp430x54xA_uscib0_i2c_11.c***\n"+


                "#include <msp430.h>\n"+

                "unsigned char *PRxData;                     // Pointer to RX data\n"+
                "unsigned char RXByteCtr;\n"+
                "volatile unsigned char RxBuffer[128];       // Allocate 128 byte of RAM\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                " UCB0CTL0 = UCMST + UCMODE_3 + UCSYNC;     // I2C Master, synchronous mode\n"+
                "  UCB0CTL1 = UCSSEL_2 + UCSWRST;            // Use SMCLK, keep SW reset\n"+
                "  UCB0BR0 = 12;                             // fSCL = SMCLK/12 = ~100kHz\n"+
                "  UCB0BR1 = 0;\n"+
                "  UCB0I2CSA = 0x48;                         // Slave Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCRXIE;                         // Enable RX interrupt\n"+

                "  while (1)\n"+
                "  {\n"+
                "   PRxData = (unsigned char *)RxBuffer;    // Start of RX buffer\n"+
                "    RXByteCtr = 5;                          // Load RX byte counter\n"+
                "    while (UCB0CTL1 & UCTXSTP);             // Ensure stop condition got sent\n"+
                "    UCB0CTL1 |= UCTXSTT;                    // I2C start condition\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "                                            // Remain in LPM0 until all data\n"+
                "                                            // is RX'd\n"+
                "    __no_operation();                       // Set breakpoint >>here<< and\n"+
                "  }                                         // read out the RxBuffer buffer\n"+
                "}\n"+

                "//-------------------------------------------------------------------------------\n"+
                "// The USCI_B0 data ISR is used to move received data from the I2C slave\n"+
                "// to the MSP430 memory. It is structured such that it can be used to receive\n"+
                "// any 2+ number of bytes by pre-loading RXByteCtr with the byte count.\n"+
                "//-------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                " {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                " case  6: break;                           // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "    RXByteCtr--;                            // Decrement RX byte counter\n"+
                "    if (RXByteCtr)\n"+
                "    {\n"+
                "      *PRxData++ = UCB0RXBUF;               // Move RX data to address PRxData\n"+
                "      if (RXByteCtr == 1)                   // Only one byte left?\n"+
                "        UCB0CTL1 |= UCTXSTP;                // Generate I2C stop condition\n"+
                "    }\n"+
                "    else\n"+
                "    {\n"+
                "      *PRxData = UCB0RXBUF;                 // Move final RX data to PRxData\n"+
                "      __bic_SR_register_on_exit(LPM0_bits); // Exit active CPU\n"+
                "    }\n"+
                "    break; \n"+
                "  case 12: break;                           // Vector 12: TXIFG\n"+
                "  default: break; \n"+
                "  }\n"+
                "}\n";


        static String program82=   "//  MSP430F543xA Demo - USCI_B0 I2C Slave TX multiple bytes to MSP430 Master\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The slave\n"+
                "//  transmits to the master. This is the slave code. The interrupt driven\n"+
                "//  data transmission is demonstrated using the USCI_B0 TX interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO = ~1.045MHz\n"+
                "//\n"+



                "#include <msp430.h>\n"+

                "unsigned char *PTxData;                     // Pointer to TX data\n"+
                "const unsigned char TxData[] =              // Table of data to transmit\n"+
                "{\n"+
                "  0x11,\n"+
                "  0x22,\n"+
                "  0x33,\n"+
                "  0x44,\n"+
                "  0x55\n"+
                "};\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMODE_3 + UCSYNC;             // I2C Slave, synchronous mode\n"+
                "  UCB0I2COA = 0x48;                         // Own Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCTXIE + UCSTPIE + UCSTTIE;     // Enable STT and STP interrupt\n"+
                "                                            // Enable TX interrupt\n"+

                "  while (1)\n"+
                "  {\n"+
                "    PTxData = (unsigned char *)TxData;      // Start of TX buffer\n"+

                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "                                            // Remain in LPM0 until master\n"+
                "                                            // finishes RX\n"+
                "    __no_operation();                       // Set breakpoint >>here<< and\n"+
                "  }                                         // read out the TXByteCtr counter\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 data ISR TX vector is used to move data from MSP430 memory to the\n"+
                "// I2C master. PTxData points to the next byte to be transmitted, and TXByteCtr\n"+
                "// keeps track of the number of bytes transmitted.\n"+
                "//------------------------------------------------------------------------------\n"+
                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 state ISR TX vector is used to wake up the CPU from LPM0 in order\n"+
                "// to do processing in the main program after data has been transmitted. LPM0 is\n"+
                "// only exit in case of a (re-)start or stop condition when actual data\n"+
                "// was transmitted.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6:                                  // Vector  6: STTIFG\n"+
                "    UCB0IFG &= ~UCSTTIFG;                   // Clear start condition int flag\n"+
                "    break;\n"+
                "  case  8:                                  // Vector  8: STPIFG\n"+
                "    UCB0IFG &= ~UCSTPIFG;                   // Clear stop condition int flag\n"+
                "    __bic_SR_register_on_exit(LPM0_bits);   // Exit LPM0 if data was transmitted\n"+
                "    break;\n"+
                "  case 10: break;                           // Vector 10: RXIFG\n"+
                "  case 12:                                  // Vector 12: TXIFG  \n"+
                "    UCB0TXBUF = *PTxData++;                 // Transmit data at address PTxData\n"+
                "    break;\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";



        static String program83=    "//  MSP430F543xA Demo - USCI_B0 I2C Master TX multiple bytes to MSP430 Slave\n"+
                "//						using a repeated restart.\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  transmits to the slave, issues a repeated restart, and then receives data\n"+
                "//  from the slave. This is the MASTER CODE. It continuously transmits an array\n"+
                "//  of data and demonstrates how to implement an I2C master transmitter sending\n"+
                "//  multiple bytes using the USCI_B0 TX interrupt followed by a master receiver \n"+
                "//  receiving multiple bytes using the USCI_B0 RX Interrupt.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = BRCLK = default DCO = ~1.045MHz\n"+



                "#include <msp430.h>\n"+

                "unsigned char *PTxData;                     // Pointer to TX data\n"+
                "unsigned char TXByteCtr;\n"+

                "const unsigned char TxData[] =              // Table of data to transmit\n"+
                "{\n"+
                "  0x11,\n"+
                "  0x22,\n"+
                "  0x33,\n"+
                "  0x44,\n"+
                "  0x55\n"+
                "};\n"+

                "unsigned char *PRxData;                     // Pointer to RX data\n"+
                "unsigned char RXByteCtr;\n"+
                "volatile unsigned char RxBuffer[128];       // Allocate 128 byte of RAM\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMST + UCMODE_3 + UCSYNC;     // I2C Master, synchronous mode\n"+
                "  UCB0CTL1 = UCSSEL_2 + UCSWRST;            // Use SMCLK, keep SW reset\n"+
                "  UCB0BR0 = 12;                             // fSCL = SMCLK/12 = ~100kHz\n"+
                "  UCB0BR1 = 0;\n"+
                "  UCB0I2CSA = 0x48;                         // Slave Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCTXIE + UCRXIE;                // Enable TX and RX interrupt\n"+

                "  PTxData = (unsigned char *)TxData;      // TX array start address\n"+
                "                                          // Place breakpoint here to see each\n"+
                "                                          // transmit operation.\n"+
                "  TXByteCtr = sizeof TxData;              // Load TX byte counter\n"+

                "  PRxData = (unsigned char *)RxBuffer;    // Start of RX buffer\n"+
                "  RXByteCtr = 5;                          // Load RX byte counter\n"+

                "  UCB0CTL1 |= UCTR + UCTXSTT;             // I2C TX, start condition\n"+

                "  while (1)\n"+
                "  {\n"+
                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "  }\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCIAB0TX_ISR is structured such that it can be used to transmit any\n"+
                "// number of bytes by pre-loading TXByteCtr with the byte count. Also, TXData\n"+
                "// points to the next byte to transmit.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;                           // Vector  2: ALIFG\n"+
                "  case  4: break;                           // Vector  4: NACKIFG\n"+
                "  case  6: break;   		                // Vector  6: STTIFG\n"+
                "  case  8: break;                           // Vector  8: STPIFG\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "    RXByteCtr--;                            // Decrement RX byte counter\n"+
                "    if (RXByteCtr)\n"+
                "    {\n"+
                "      *PRxData++ = UCB0RXBUF;               // Move RX data to address PRxData\n"+
                "    }\n"+
                "    else\n"+
                "    {\n"+
                "      *PRxData = UCB0RXBUF;                 // Move final RX data to PRxData\n"+
                "      UCB0CTL1 |= UCTR + UCTXSTT;           // I2C TX, start condition\n"+
                "      PRxData = (unsigned char *)RxBuffer;    // Start of RX buffer\n"+
                "      RXByteCtr = 5;                          // Load RX byte counter\n"+
                "    }\n"+
                "    break;\n"+
                "  case 12:                                  // Vector 12: TXIFG\n"+
                "    if (TXByteCtr)                          // Check TX byte counter\n"+
                "    {\n"+
                "      UCB0TXBUF = *PTxData++;               // Load TX buffer\n"+
                "      TXByteCtr--;                          // Decrement TX byte counter\n"+
                "    }\n"+
                "    else\n"+
                "    {\n"+
                "        PTxData = (unsigned char *)TxData;  // TX array start address\n"+
                "                                            // Place breakpoint here to see each\n"+
                "                                            // transmit operation.\n"+
                "        TXByteCtr = sizeof TxData;          // Load TX byte counter\n"+

                "        UCB0CTL1 &= ~UCTR;         			// I2C RX\n"+
                "        UCB0CTL1 |= UCTXSTT;         		// I2C start condition\n"+

                "        UCB0TXBUF = *PTxData++;             // Load TX buffer\n"+
                "        TXByteCtr--;                        // Decrement TX byte counter\n"+
                "    }\n"+
                "  default: break;\n"+
                "  }\n"+
                "}\n";


        static String program84=   "//  MSP430F543xA Demo - USCI_B0 I2C Slave RX multiple bytes from MSP430 Master\n"+
                "//						using a repeated restart.\n"+
                "//\n"+
                "//  Description: This demo connects two MSP430's via the I2C bus. The master\n"+
                "//  transmits to the slave, issues a repeated restart, and then receives data\n"+
                "//  from the slave. This is the SLAVE CODE. The interrupt driven data reception\n"+
                "//  is demonstrated using the USCI_B0 RX interrupt.  The interrupt driven data\n"+
                "//  transmission is demonstrated using the USCI_B0 TX interrupt.  The USCI_B0\n"+
                "//  STT interrupt is used to load the transmit buffer if the slave is configured\n"+
                "//  as a transmitter.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO = ~1.045MHz\n"+



                "#include <msp430.h>\n"+

                "unsigned char *PRxData;                     // Pointer to RX data\n"+
                "unsigned char RXByteCtr;\n"+
                "volatile unsigned char RxBuffer[128];       // Allocate 128 byte of RAM\n"+
                "unsigned char temp = 0;\n"+

                "unsigned char *PTxData;                     // Pointer to TX data\n"+
                "const unsigned char TxData[] =              // Table of data to transmit\n"+
                "{\n"+
                "  0x66,\n"+
                "  0x77,\n"+
                "  0x88,\n"+
                "  0x99,\n"+
                "  0xAA\n"+
                "};\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT\n"+
                "  P3SEL |= 0x06;                            // Assign I2C pins to USCI_B0\n"+
                "  UCB0CTL1 |= UCSWRST;                      // Enable SW reset\n"+
                "  UCB0CTL0 = UCMODE_3 + UCSYNC;             // I2C Slave, synchronous mode\n"+
                "  UCB0I2COA = 0x48;                         // Own Address is 048h\n"+
                "  UCB0CTL1 &= ~UCSWRST;                     // Clear SW reset, resume operation\n"+
                "  UCB0IE |= UCSTPIE + UCSTTIE + UCRXIE + UCTXIE;\n"+
                "                                            // Enable STT, STP, RX & TX interrupt\n"+

                "  PRxData = (unsigned char *)RxBuffer;      // Start of RX buffer\n"+
                "  RXByteCtr = 0;                            // Clear RX byte count\n"+

                "  PTxData = (unsigned char *)TxData;        // Start of TX buffer\n"+

                "  while (1)\n"+
                "  {\n"+
                "    __bis_SR_register(LPM0_bits + GIE);     // Enter LPM0, enable interrupts\n"+
                "  }\n"+
                "}\n"+

                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 data ISR RX vector is used to move received data from the I2C\n"+
                "// master to the MSP430 memory.\n"+
                "//------------------------------------------------------------------------------\n"+
                "//------------------------------------------------------------------------------\n"+
                "// The USCI_B0 state ISR TX vector is used to wake up the CPU from LPM0 in order\n"+
                "// to process the received data in the main program. LPM0 is only exit in case\n"+
                "// of a (re-)start or stop condition when actual data was received.\n"+
                "//------------------------------------------------------------------------------\n"+
                "#pragma vector = USCI_B0_VECTOR\n"+
                "__interrupt void USCI_B0_ISR(void)\n"+
                "{\n"+
                "  switch(__even_in_range(UCB0IV,12))\n"+
                "  {\n"+
                "  case  0: break;                           // Vector  0: No interrupts\n"+
                "  case  2: break;							// Vector  2: ALIFG\n"+
                "  case  4: break;		                    // Vector  4: NACKIFG\n"+
                "  case  6:                                  // Vector  6: STTIFG\n"+
                "    if (UCB0CTL1 & UCTR){\n"+
                "    	PTxData = (unsigned char *)TxData;  // Start of TX buffer\n"+
                "		UCB0TXBUF = *PTxData++;			    // Transmit data at address PTxData\n"+
                "    }\n"+
                "    break;\n"+
                "  case  8:                                  // Vector  8: STPIFG\n"+
                "    break;\n"+
                "  case 10:                                  // Vector 10: RXIFG\n"+
                "	*PRxData++ = UCB0RXBUF;                 // Get RX'd byte into buffer\n"+
                "	RXByteCtr++;\n"+
                "    break;\n"+
                "  case 12:                                  // Vector 12: TXIFG\n"+
                "    UCB0TXBUF = *PTxData++;                 // Transmit data at address PTxData\n"+
                "    break;\n"+
                "  default: break;\n"+
                "  }  \n"+
                "}\n";



        static String program85=  "//  MSP430FG54x Demo - WDT, Toggle P1.0, Interval Overflow ISR, DCO SMCLK\n"+
                "//  Description: Toggle P1.0 using software timed by the WDT ISR. Toggle rate\n"+
                "//  is approximately 30ms = {(default DCO 1.045MHz) / 32768} based on default\n"+
                "//  DCO/SMCLK clock source used in this example for the WDT.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_MDLY_32;                     // WDT 32ms, SMCLK, interval timer \n"+
                "  SFRIE1 |= WDTIE;                          // Enable WDT interrupt\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+

                "  __bis_SR_register(LPM0_bits + GIE);       // Enter LPM0, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Watchdog Timer interrupt service routine\n"+
                "#pragma vector=WDT_VECTOR\n"+
                "__interrupt void WDT_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0 (LED)\n"+
                "}\n";


        static String program86=   "//   MSP430F543xA Demo - WDT, Toggle P1.0, Interval Overflow ISR, 32kHz ACLK\n"+
                "//  Description: Toggle P1.0 using software timed by WDT ISR. Toggle rate is\n"+
                "//  exactly 250ms based on 32kHz ACLK WDT clock source. In this example the\n"+
                "//  WDT is configured to divide 32768 watch-crystal(2^15) by 2^13 with an ISR\n"+
                "//  triggered @ 4Hz = [WDT CLK source/32768].\n"+
                "//  ACLK = REFO , MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  WDTCTL = WDT_ADLY_250;                    // WDT 250ms, ACLK, interval timer\n"+
                "  SFRIE1 |= WDTIE;                          // Enable WDT interrupt\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+

                "  __bis_SR_register(LPM3_bits + GIE);       // Enter LPM3, enable interrupts\n"+
                "  __no_operation();                         // For debugger\n"+
                "}\n"+

                "// Watchdog Timer interrupt service routine\n"+
                "#pragma vector = WDT_VECTOR\n"+
                "__interrupt void WDT_ISR(void)\n"+
                "{\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0 using exclusive-OR\n"+
                "}\n";




        static String program87=   "//  MSP430F543xA Demo - WDT+ Failsafe Clock, WDT mode, DCO SMCLK\n"+
                "//  Description; Allow WDT+ in watchdog mode to timeout. Toggle P1.0 in main\n"+
                "//  function. LPM4 is entered, this example will demonstrate WDT+ feature\n"+
                "//  of preventing WDT+ clock to be disabled.\n"+
                "//  The WDT+ will not allow active WDT+ clock to be disabled by software, the\n"+
                "//  LED continues to Flash because the WDT times out normally (in 32768 DCOCLK\n"+
                "//  cycles) even though software has attempted to disable WDT+ clock source.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO ~1.045MHz\n"+


                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                "  P1DIR |= 0x01;                            // Set P1.0 to output - SET BREAKPOINT HERE\n"+
                "  P1OUT ^= 0x01;                            // Toggle P1.0\n"+

                "  __bis_SR_register(LPM4_bits + GIE);       // Enter LPM4, Stop all clocks\n"+
                "__no_operation();                         // For debugger  \n"+
                "}\n";


        static String program88=    "//  MSP430F543xA Demo - Reset on Invalid Address fetch, Toggle P1.0\n"+
                "//\n"+
                "//  Description: This program demonstrates how a reset is executed if the CPU\n"+
                "//  tries to fetch instructions from within the module register memory address\n"+
                "//  range (0x0100 --0x0FEF) or from within unused address ranges. Toggle P1.0\n"+
                "//  by xor'ing P1.0 inside of a software loop that ends with TAR loaded with\n"+
                "//  3FFFh - op-code for 'jmp $'. This simulates a code error. The MSP430F5438A\n"+
                "//  will force a reset because it will not allow a fetch from within the address\n"+
                "//  range of the peripheral memory, as is seen by return to the mainloop and\n"+
                "//  LED flash.\n"+
                "//  ACLK = n/a, MCLK = SMCLK = default DCO ~1.045MHz\n"+

                "#include <msp430.h>\n"+

                "int main(void)\n"+
                "{\n"+
                " WDTCTL = WDTPW + WDTHOLD;                 // Stop watchdog timer - SET BREAKPOINT HERE\n"+
                " P1DIR |= 0x01;                            // Set P1.0 to output direction\n"+
                "  TA0R = 0x3FFF;                            // Valid opcode (for 'jmp $')\n"+

                " while(1)\n"+
                " {\n"+
                "   P1OUT ^= 0x01;                          // Toggle P1.0 using exclusive-OR\n"+

                "   __delay_cycles(50000);                  // Delay loop\n"+

                "   // C code to directly call an address location\n"+
                "   ((void (*)())0x350)();                  // Invalid fetch ('call #0350h')\n"+

                "  /* 0x350 is address of TA0R register and is within the module register memory\n"+
                "  address range (0x0100 --0x0FEF) */\n"+
                "}\n"+
                "}\n";

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        for(int i = 0; i< 88; i++)
        {
            b[i] = (Button) view.findViewById(buttonId[i]);
            b[i].setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        int btnid = v.getId();
        Intent i;
        //Toast.makeText(this.getActivity(),"Button is clicked!", Toast.LENGTH_LONG).show();
        switch (btnid)
        {
            case R.id.button1:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c1", program1);
                startActivity(i);
                break;
            case R.id.button2:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c2", program2);
                startActivity(i);
                break;
            case R.id.button3:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c3", program3);
                startActivity(i);
                break;
            case R.id.button4:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c4", program4);
                startActivity(i);
                break;
            case R.id.button5:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c5", program5);
                startActivity(i);
                break;
            case R.id.button6:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c6", program6);
                startActivity(i);
                break;
            case R.id.button7:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c7", program7);
                startActivity(i);
                break;
            case R.id.button8:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c8", program8);
                startActivity(i);
                break;
            case R.id.button9:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c9", program9);
                startActivity(i);
                break;
            case R.id.button10:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c10", program10);
                startActivity(i);
                break;
            case R.id.button11:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c11", program11);
                startActivity(i);
                break;
            case R.id.button12:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c12", program12);
                startActivity(i);
                break;
            case R.id.button13:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c13", program13);
                startActivity(i);
                break;
            case R.id.button14:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c14", program14);
                startActivity(i);
                break;
            case R.id.button15:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c15", program15);
                startActivity(i);
                break;
            case R.id.button16:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c16", program16);
                startActivity(i);
                break;
            case R.id.button17:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c17", program17);
                startActivity(i);
                break;
            case R.id.button18:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c18", program18);
                startActivity(i);
                break;
            case R.id.button19:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c19", program19);
                startActivity(i);
                break;
            case R.id.button20:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c20", program20);
                startActivity(i);
                break;
            case R.id.button21:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c21", program21);
                startActivity(i);
                break;
            case R.id.button22:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c22", program22);
                startActivity(i);
                break;
            case R.id.button23:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c23", program23);
                startActivity(i);
                break;
            case R.id.button24:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c24", program24);
                startActivity(i);
                break;
            case R.id.button25:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c25", program25);
                startActivity(i);
                break;
            case R.id.button26:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c26", program26);
                startActivity(i);
                break;
            case R.id.button27:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c27", program27);
                startActivity(i);
                break;
            case R.id.button28:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c28", program28);
                startActivity(i);
                break;
            case R.id.button29:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c29", program29);
                startActivity(i);
                break;
            case R.id.button30:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c30", program30);
                startActivity(i);
                break;
            case R.id.button31:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c31", program31);
                startActivity(i);
                break;
            case R.id.button32:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c32", program32);
                startActivity(i);
                break;
            case R.id.button33:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c33", program33);
                startActivity(i);
                break;
            case R.id.button34:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c34", program34);
                startActivity(i);
                break;
            case R.id.button35:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c35", program35);
                startActivity(i);
                break;
            case R.id.button36:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c36", program36);
                startActivity(i);
                break;
            case R.id.button37:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c37", program37);
                startActivity(i);
                break;
            case R.id.button38:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c38", program38);
                startActivity(i);
                break;
            case R.id.button39:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c39", program39);
                startActivity(i);
                break;
            case R.id.button40:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c40", program40);
                startActivity(i);
                break;
            case R.id.button41:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c41", program41);
                startActivity(i);
                break;
            case R.id.button42:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c42", program42);
                startActivity(i);
                break;
            case R.id.button43:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c43", program43);
                startActivity(i);
                break;
            case R.id.button44:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c44", program44);
                startActivity(i);
                break;
            case R.id.button45:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c45", program45);
                startActivity(i);
                break;
            case R.id.button46:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c46", program46);
                startActivity(i);
                break;
            case R.id.button47:
                i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c47", program47);
                startActivity(i);
                break;

            case R.id.button48:
                 i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c48", program48);
                 startActivity(i);
                 break;

            case R.id.button49:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c49", program49);
                    startActivity(i);
                    break;

            case R.id.button50:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c50", program50);
                    startActivity(i);
                    break;

            case R.id.button51:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c51", program51);
                    startActivity(i);
                    break;
            case R.id.button52:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c52", program52);
                    startActivity(i);
                    break;

            case R.id.button53:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c53", program53);
                    startActivity(i);
                    break;

            case R.id.button54:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c54", program54);
                    startActivity(i);
                    break;

            case R.id.button55:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c55", program55);
                    startActivity(i);
                    break;
            case R.id.button56:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c56", program56);
                    startActivity(i);
                    break;

            case R.id.button57:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c57", program57);
                    startActivity(i);
                    break;

            case R.id.button58:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c58", program58);
                    startActivity(i);
                    break;

            case R.id.button59:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c59", program59);
                    startActivity(i);
                    break;
            case R.id.button60:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c60", program60);
                    startActivity(i);
                    break;

            case R.id.button61:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c61", program61);
                    startActivity(i);
                    break;

            case R.id.button62:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c62", program62);
                    startActivity(i);
                    break;

            case R.id.button63:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c63", program63);
                    startActivity(i);
                    break;
            case R.id.button64:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c64", program64);
                    startActivity(i);
                    break;

            case R.id.button65:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c65", program65);
                    startActivity(i);
                    break;

            case R.id.button66:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c66", program66);
                    startActivity(i);
                    break;

            case R.id.button67:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c67", program67);
                    startActivity(i);
                    break;
            case R.id.button68:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c68", program68);
                    startActivity(i);
                    break;

            case R.id.button69:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c69", program69);
                    startActivity(i);
                    break;

            case R.id.button70:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c70", program70);
                    startActivity(i);
                    break;

            case R.id.button71:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c71", program71);
                    startActivity(i);
                    break;
            case R.id.button72:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c72", program72);
                    startActivity(i);
                    break;

            case R.id.button73:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c73", program73);
                    startActivity(i);
                    break;

            case R.id.button74:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c74", program74);
                    startActivity(i);
                    break;

            case R.id.button75:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c75", program75);
                    startActivity(i);
                    break;
            case R.id.button76:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c76", program76);
                    startActivity(i);
                    break;

            case R.id.button77:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c77", program77);
                    startActivity(i);
                    break;

            case R.id.button78:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c78", program78);
                    startActivity(i);
                    break;

            case R.id.button79:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c79", program79);
                    startActivity(i);
                    break;

            case R.id.button80:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c80", program80);
                    startActivity(i);
                    break;
            case R.id.button81:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c81", program81);
                    startActivity(i);
                    break;

            case R.id.button82:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c82", program82);
                    startActivity(i);
                    break;

            case R.id.button83:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c83", program83);
                    startActivity(i);
                    break;

            case R.id.button84:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c84", program84);
                    startActivity(i);
                    break;

            case R.id.button85:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c85", program85);
                    startActivity(i);
                    break;

            case R.id.button86:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c86", program86);
                    startActivity(i);
                    break;

            case R.id.button87:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c87", program85);
                    startActivity(i);
                    break;

            case R.id.button88:
                    i = new Intent(this.getActivity(),Disclaimer.class); i.putExtra("c88", program88);
                    startActivity(i);
                    break;
        }

    }
}
