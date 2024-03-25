package bend_QRcodewifi.bend_QRcodewifi.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredenzRequest {


    private String rete;
    private String password;
    private String type;
}