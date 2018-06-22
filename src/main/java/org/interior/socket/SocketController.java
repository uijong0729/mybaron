package org.interior.socket;

import org.interior.socket.vo.Chatlog;
import org.interior.socket.vo.Message;
import org.interior.socket.vo.Nickname;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SocketController {


    @MessageMapping("/insertLog")
    @SendTo("/subscribe/readLog")
    public Chatlog greeting(Message msg, Nickname nick) throws Exception {
        
    	return new Chatlog(HtmlUtils.htmlEscape(msg.getMsg()), HtmlUtils.htmlEscape(nick.getNick()));
    }

}
