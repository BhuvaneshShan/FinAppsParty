# FinAppsParty
Mobile app to ease the process of mobile payment

You need not take the phone out of your pocket to pay.
Lesser interaction than tap, nfc, swipe etc
Developed at GlobalPayments' FinAppsParty on March 11 and 12, 2016

Push notification sent using google cloud messaging
POST request: https://gcm-http.googleapis.com/gcm/send
Headers:
  Content-Type : application/json
  Authorization : key= [project-api-key]
Body:
{ "data": {    
          "message": { 
                  "VendorName":"Publix-Atl","VendorId":"101901","Amount":"456","longitude":"51.50","latitude":"-0.21"
                   },
                  "time": "15:10"
          },
"to": "[registered app's token goes here]"
}
