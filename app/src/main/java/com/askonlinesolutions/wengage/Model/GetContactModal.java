package com.askonlinesolutions.wengage.Model;

import java.util.List;

public class GetContactModal {
    /**
     * status : 1
     * message : Your Invitations And Contacts List.
     * contactsList : {"myInvitations":[{"contactId":"con:218&199","userId":199,"fullName":"Mark","knownByName":"Olessia","email":"mark.grinshpun@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545070710924_754284.jpg","influencer":0,"pendingReqStatus":"SENT"},{"contactId":"con:218&217","userId":217,"fullName":"Jenny","knownByName":"Jenny","email":"ppan122@hotmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545848109603_200445.jpg","influencer":0,"pendingReqStatus":"SENT"}],"myContacts":[{"contactId":"con:175&218","userId":175,"fullName":"Anthony Scalzo12","knownByName":"Anthony","email":"anthony@myconxn.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1543952159589_668922.jpg","influencer":0},{"contactId":"con:218&221","userId":221,"fullName":"Marwin","knownByName":"Marwin","email":"rohit.askonline@gmail.com","photoURL":"https://s3.amazonaws.com/wengageapp/profile/1545940880496_420809.jpg","influencer":0}]}
     */

    private int status;
    private String message;
    private ContactsListBean contactsList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactsListBean getContactsList() {
        return contactsList;
    }

    public void setContactsList(ContactsListBean contactsList) {
        this.contactsList = contactsList;
    }

    public static class ContactsListBean {
        private List<ContactsListBean.MyInvitationsBean> myInvitations;
        private List<ContactsListBean.MyContactsBean> myContacts;

        public List<ContactsListBean.MyInvitationsBean> getMyInvitations() {
            return myInvitations;
        }

        public void setMyInvitations(List<ContactsListBean.MyInvitationsBean> myInvitations) {
            this.myInvitations = myInvitations;
        }

        public List<ContactsListBean.MyContactsBean> getMyContacts() {
            return myContacts;
        }

        public void setMyContacts(List<ContactsListBean.MyContactsBean> myContacts) {
            this.myContacts = myContacts;
        }

        public static class MyInvitationsBean {
            /**
             * contactId : con:218&199
             * userId : 199
             * fullName : Mark
             * knownByName : Olessia
             * email : mark.grinshpun@gmail.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1545070710924_754284.jpg
             * influencer : 0
             * pendingReqStatus : SENT
             */

            private String contactId;
            private int userId;
            private String fullName;
            private String knownByName;
            private String email;
            private String photoURL;
            private int influencer;
            private String pendingReqStatus;

            public String getContactId() {
                return contactId;
            }

            public void setContactId(String contactId) {
                this.contactId = contactId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getKnownByName() {
                return knownByName;
            }

            public void setKnownByName(String knownByName) {
                this.knownByName = knownByName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhotoURL() {
                return photoURL;
            }

            public void setPhotoURL(String photoURL) {
                this.photoURL = photoURL;
            }

            public int getInfluencer() {
                return influencer;
            }

            public void setInfluencer(int influencer) {
                this.influencer = influencer;
            }

            public String getPendingReqStatus() {
                return pendingReqStatus;
            }

            public void setPendingReqStatus(String pendingReqStatus) {
                this.pendingReqStatus = pendingReqStatus;
            }
        }

        public static class MyContactsBean {
            /**
             * contactId : con:175&218
             * userId : 175
             * fullName : Anthony Scalzo12
             * knownByName : Anthony
             * email : anthony@myconxn.com
             * photoURL : https://s3.amazonaws.com/wengageapp/profile/1543952159589_668922.jpg
             * influencer : 0
             */

            private String contactId;


            private boolean isSelected;
            private int userId;
            private String fullName;
            private String knownByName;
            private String email;
            private String photoURL;
            private int influencer;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getContactId() {
                return contactId;
            }

            public void setContactId(String contactId) {
                this.contactId = contactId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getKnownByName() {
                return knownByName;
            }

            public void setKnownByName(String knownByName) {
                this.knownByName = knownByName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhotoURL() {
                return photoURL;
            }

            public void setPhotoURL(String photoURL) {
                this.photoURL = photoURL;
            }

            public int getInfluencer() {
                return influencer;
            }

            public void setInfluencer(int influencer) {
                this.influencer = influencer;
            }
        }
    }
}
