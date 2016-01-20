welcome = { ->
    def hostName;
    try {
        hostName = java.net.InetAddress.getLocalHost().getHostName();
    } catch (java.net.UnknownHostException ignore) {
        hostName = "localhost";
    }
    return """
__      __ _____  _____               _  _         _    
` `    / /|_   _||  __ `    /`       | |(_)       | |   
 ` `  / /   | |  | |  | |  /  `      | | _  _ __  | | __
  ` `/ /    | |  | |  | | / /` `     | || || '_ ` | |/ /
   `  /    _| |_ | |__| |/ ____ `  _ | || || | | ||   ' 
    `/    |_____||_____//_/    `_`(_)|_||_||_| |_||_|`_`\n\

Welcome to $hostName ! \n\
${new Date()}.
""";
}

prompt = { ->
    return "vida.link: ";
}
