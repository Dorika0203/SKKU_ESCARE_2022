package com.example.kakaotest.fortanix;

import com.fortanix.sdkms.v1.ApiClient;
import com.fortanix.sdkms.v1.ApiException;
import com.fortanix.sdkms.v1.api.*;
import com.fortanix.sdkms.v1.auth.ApiKeyAuth;
import com.fortanix.sdkms.v1.model.*;

import java.nio.ByteBuffer;
import java.security.Key;
import java.util.List;

public class FortanixRestApi {
    public static ApiClient createClient(String server, String username, String password){
        ApiClient client = new ApiClient();
        client.setBasePath(server);
        client.setUsername(username);
        client.setPassword(password);
        return client;
    }

    public static ApiClient generateFortanixSDKMSClientAndVerify(String server, String username, String password) {
        ApiClient client = new ApiClient();
        client.setBasePath(server);
        client.setUsername(username);
        client.setPassword(password);
        updateBearerToken(client);
        return client;
    }

    // BearerToken update.
    public static boolean updateBearerToken(ApiClient client) {
        AuthenticationApi authenticationApi = new AuthenticationApi(client);
        AuthResponse authResponse;
        try {
            authResponse = authenticationApi.authorize();
            ApiKeyAuth bearerTokenAuth = (ApiKeyAuth) client.getAuthentication("bearerToken");
            bearerTokenAuth.setApiKey(authResponse.getAccessToken());
            bearerTokenAuth.setApiKeyPrefix("Bearer");
            SelectAccountRequest request = new SelectAccountRequest().acctId("fb61113e-badd-4b14-9a64-aa9bb4de9058");
            authenticationApi.selectAccount(request);
            System.out.println("Bearer Token authentication SUCCESS...");
            return true;
        } catch (ApiException e) {
            System.err.println("Bearer Token authentication FAIL..." + e.getMessage());
            return false;
        }
    }

    public static Group getGroupsById(ApiClient client, String groupId) throws ApiException {
        GroupsApi groupsApi = new GroupsApi(client);
        Group group = groupsApi.getGroup(groupId);
        return group;
    }

    public static List<KeyObject> getSecurityObjects(ApiClient client) throws ApiException {
        SecurityObjectsApi securityObjectsApi = new SecurityObjectsApi(client);
        List<KeyObject> keyObjects = securityObjectsApi.getSecurityObjects(null,null,null);
        return keyObjects;
    }

    public static KeyObject getSecurityObjectByName(ApiClient client, String sobjectName) throws ApiException {
        SecurityObjectsApi securityObjectsApi = new SecurityObjectsApi(client);
        KeyObject keyObject;
        List<KeyObject> keyObjects = securityObjectsApi.getSecurityObjects(null,null,null);
        for(int i=0; i<keyObjects.size(); i++){
            if(keyObjects.get(i).getName().equals(sobjectName)){
                keyObject = keyObjects.get(i);
                return keyObject;
            }
        }
        return null;
    }

    public static Plugin getPluginByName(ApiClient client, String groupId, String pluginName) throws ApiException {
        PluginsApi pluginsApi = new PluginsApi(client);
        List<Plugin> plugins = pluginsApi.getPlugins(groupId);
        Plugin plugin;
        for(int i=0; i<plugins.size(); i++){
            if(plugins.get(i).getName().equals(pluginName)){
                plugin = plugins.get(i);
                return plugin;
            }
        }
        return null;
    }

    public static String invokePlugin(ApiClient client, String pluginId, String object) throws ApiException {
        PluginInvokeRequest pluginInvokeRequest = new PluginInvokeRequest(object);
        PluginsApi pluginsApi = new PluginsApi(client);
        PluginInvokeResponse pluginInvokeResponse = pluginsApi.invokePlugin(pluginId, pluginInvokeRequest);
        String response = pluginInvokeResponse.getJsonBody();
        return response;
    }

    public static String getUserEmail(ApiClient client, String userId) throws  ApiException {
        UsersApi usersApi = new UsersApi(client);
        User user = usersApi.getUser(userId);
        String email = user.getUserEmail();
        return email;
    }
}
