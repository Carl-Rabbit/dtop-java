package grp.dtop.dtopjava.servise;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String echo(String recv) {
        return recv;
    }
}
