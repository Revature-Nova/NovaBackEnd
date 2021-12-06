package com.revature.nova.filters;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class RouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
