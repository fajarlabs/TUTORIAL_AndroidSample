package com.spasi.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spasi.android.util.RSASecurity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        final TextView editTextRSA = (TextView) findViewById(R.id.editTextRSA);

        Button btnRSAEncrypt = (Button) findViewById(R.id.btnRSAEncrypt);
        btnRSAEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    String b64PrivateKey = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQ0KTUlJQ1hBSUJBQUtCZ1FDMUpjaEZXdjNUMFNiK21RM0haTXVCZnF1M1hKTmk1d3JiUXovUzlzYXJvc3NTdDErSQ0KZzhvN21VNGZJd3RNSGQ2TXBrL3BlQmtLNFRHb2N4M292ZWs4VFRpKzBHTkhGb2pNWVNGM3FvVTlSMmVpdGZuTw0KMDlzTkRpRHlXcVE0dS8vUWlhd3A3Q3o1MExuS3lRVXpNTjVUMFFyamNtVUNvRGg3SGxLZU9jaDljUUlEQVFBQg0KQW9HQUJlbnlxNmw2b0l6N0tYd09rQ28zTHQ1SFhiNUw2VUplMTAxTzdzeXJ6Q0FOb2tuNmFHVDVqK2dyamM2Tg0KaEkzVDFYcUxRTFZ4RjFybVhiQUs2NTJTR1AzZC9SelhhRTc2b2dMN1RKS2YxaG1VOFd0RkM3MmtCT1ZzdVg4MQ0KOEdvM1FkeFlESnpDSHBFL3JkNHlOS1NZRHpIZmJxQVU4bTFMV1o1L0RoNWQxZUVDUVFETXZybTdKS1hSSXp5MA0KVjBHY3pubG1XWHpBMlJCZ2hWcVJxYVNLZkpLQzRBZm04L2hqUEM1My93YlVWU3lEemhPUHBoUmZjTkpycWpqVA0KU2JYc2FUdmhBa0VBNG43TEdGNDh0K01QbHhpUGs0dnJHTkNXZ2lQdmxRbWp6a2orVzUySERQSHJJTFVhVHU5Rw0KVVgraStEZEp0anJkU2tGVFFhT00ybkswYmFOSnNUZnprUUpBTWY3RWxaa09VSEk5U1lBaENtdzBvclJwVFNIeg0KNk0zSFh0aEEwSHc0dm5KOEczNHBnZm5aRXY0WUtkTWVpcFRRbS93ZTcyczZSb1lMV0RTZ2tyampvUUpCQUpRMA0KNjZpeVlZRGliMTBya1NlM01zczJFb1p1QldPOHZ4ZEk1RWdHdUV4Y01NTmlQaWpRb1c3SzJsVVp1SDRwM291cw0KVzFKa050THFvKzNxSDFwN2hrRUNRRGFabm94VWhaZ29YcXA2VGdUM1EzOUFIMmREMnprazJTbVl3OFpjZkNNZQ0KZ0FDSEY0cVU0dVdNV3VreXNaS21hSUpMNzhlSHBlTHhTa2FoeTNad0NLWT0NCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0t";
//                    String b64EncryptedStr = "V2hNuSqtngn+9y2rwvh0luY7zHaHrCXEVfO+xO9zAmbP1g7LID4sdf78cF/ikpV77jACcwvl9d/QMr4yZErihhMKdm0NUqsYkfi7LsIIFUoKUzXK50LXIVF/rGborclnUOXar7dt91VqWSHcCUzRCKIMEsz/tSnv/M9x8bJVm+w=";
//                    String result = RSASecurity.decode(b64EncryptedStr,b64PrivateKey);
//                    //System.out.println(result);
                    //Map result = RSASecurity.encode("HELLO WORLD AJA");
                    String b64PrivateKey = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlDZVFJQkFEQU5CZ2txaGtpRzl3MEJB\n" +
                            "UUVGQUFTQ0FtTXdnZ0pmQWdFQUFvR0JBT1piVDNiVHFOeVcvLzNUd2xzaVNQSVVrZjhoCjB3MDZn\n" +
                            "eU5OTnZ3eWlvMjMzNFVjOHlhY0VyZStxMGUxUWVLdnlPSmxxL2NXNFhlbjh3Si9zYzhDRTN0eCtQ\n" +
                            "MndOMUVWdGQ5a3pwNlgKaERNeUZTSnpoRmRXT3gwR2czNXhhYjE0c0dxOGxlVllaOXE4N21qdTl5\n" +
                            "ay9kb1loSGoxbWdiQ2xaWnAvZ0VmdUVSMWJBZ01CQUFFQwpnWUVBcGcwb1NSWG1ZYTBQdDE5UWVQ\n" +
                            "Vkw5QVZVQUwvWExYQUNYQTRyRnIwd0YxeDJhYlFtcXF4UzZkVXVEckRnWDVJcmt0ZUxrTUFUCm8z\n" +
                            "ZVR1emRsYXoycDRESjZXNnpqcUpLSGRTeGhFdlBybjdsR2t5aGF3SDhwcHZZQXl6Zzg1RW1TQU4y\n" +
                            "ZDY2dUFQNFltSFlTOVJHZ08KY0dhL0l0aVZoeVJuZ3ZJNjc0eFVQOUVDUVFEejZ4RTNsUkIxR1Vr\n" +
                            "alp5S2JzK2toRUhBT0dKaERkMWNqR0owZGZpR0o5Zm14RkVRZgpDaW9Bd1VMYnI2eTBodVYyaGVk\n" +
                            "MnNEV2JhUitMTnA3cmFLTkhBa0VBOGNSSHFpZkcrTTlFSTJTYTVnYklwYk5nM24yWGRpaFRXR204\n" +
                            "ClQ2T0k2Ti9SbjIxcnpzRTlzSVJPNkZYbnlWdDJIRmNtQUs0TnBIbktaWlk5eTc1M1RRSkJBTDRU\n" +
                            "L2V2Qmh2eXB3cndMQUFZMEVrVkkKNlBtakl1ellVQmd5Y3lWcmlEbFpiTVlZMCtrWVk1a0pBYy91\n" +
                            "dTNoRzh3UUUzMVkzaE43aDhjbnJ1N1laYWJzQ1FRRHVWZ0FNRjBDSQpnZE43VEsrRE9vYk5DMFBn\n" +
                            "c2xFQk00bk9iQll2TUZsRXNYaTJRU2w1WG5rUkxhMllUeSs4Q2dVcXRTTUtvb3RpYklvQmtaWUQ2\n" +
                            "QlUxCkFrRUE0dGVlanY2bGtZbG52TUdlR0Z4cXk4RGNBZlJRK3phcmhLdXd3ZWIzTXBScnhjY0kw\n" +
                            "K3lCSHBZdWFBQytVMXFhbkZjcTcyYk0KM2RLZTZNTlo5QitsRHcKCi0tLS0tRU5EIFJTQSBQUklW\n" +
                            "QVRFIEtFWS0tLS0t";

                    String b64EncryptedStr = "M7B/nZFLhoJykGmjocDqqial75LiNH6WoFU2pD/th5s3YjcPW2trDOBxwbpmsxap2vcgwET5i+Zu\n" +
                            "y9lk0ElL5X5Pa2Bb6dd/ANwLi7o7u2GjpU6QXDTXO46VpnGRWA05hLjY5KqNf2LsDAa5y6FVb4/h\n" +
                            "JJskpEu9R8kp05AuDIM";

                    String result = RSASecurity.decode(b64EncryptedStr,b64PrivateKey);
                    System.out.println(result);
                }catch (Exception e) {
                    e.printStackTrace();;
                }
            }
        });

        Button btnRSADecrypt = (Button) findViewById(R.id.btnRSADecrypt);

    }
}
