package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class WyComment {



    private boolean isMusician;
    private String userId;
    private boolean moreHot;
    private int code;
    private int total;
    private boolean more;
    private List<HotCommentsBean> hotComments;

    public List<HotCommentsBean> getHotComments() {
        return hotComments;
    }

    public void setHotComments(List<HotCommentsBean> hotComments) {
        this.hotComments = hotComments;
    }

    public String getUserId() {
        return userId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getTotal() {
        return total;
    }

    public boolean isMore() {
        return more;
    }

    public boolean isMoreHot() {
        return moreHot;
    }

    public boolean isMusician() {
        return isMusician;
    }

    public void setMoreHot(boolean moreHot) {
        this.moreHot = moreHot;
    }

    public void setMusician(boolean musician) {
        isMusician = musician;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class HotCommentsBean {
        /**
         * user : {"locationInfo":null,"liveInfo":null,"anonym":0,"avatarUrl":"http://p2.music.126.net/ubliTZpT_XEy5vzRiYFUKQ==/18797250790330390.jpg","authStatus":0,"experts":null,"vipRights":{"associator":{"vipCode":100,"rights":true},"musicPackage":null,"redVipAnnualCount":1},"userId":441846746,"userType":0,"nickname":"哲也love","vipType":11,"remarkName":null,"expertTags":null}
         * beReplied : []
         * pendantData : null
         * showFloorComment : null
         * status : 0
         * commentId : 437807342
         * content : 八年过去了，郑爽还是如八年前的流星雨一样纯洁如外表，干净而亲切，就如这歌一样清新的声音仿佛把立夏的一切描述的淋漓尽致。
         * time : 1497320494166
         * likedCount : 420979
         * expressionUrl : null
         * commentLocationType : 0
         * parentCommentId : 0
         * decoration : null
         * repliedMark : null
         * liked : false
         */

        private UserBean user;
        private Object pendantData;
        private Object showFloorComment;
        private int status;
        private String commentId;
        private String content;
        private long time;
        private int likedCount;
        private Object expressionUrl;
        private int commentLocationType;
        private int parentCommentId;
        private Object decoration;
        private Object repliedMark;
        private boolean liked;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getPendantData() {
            return pendantData;
        }

        public void setPendantData(Object pendantData) {
            this.pendantData = pendantData;
        }

        public Object getShowFloorComment() {
            return showFloorComment;
        }

        public void setShowFloorComment(Object showFloorComment) {
            this.showFloorComment = showFloorComment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getLikedCount() {
            return likedCount;
        }

        public void setLikedCount(int likedCount) {
            this.likedCount = likedCount;
        }

        public Object getExpressionUrl() {
            return expressionUrl;
        }

        public void setExpressionUrl(Object expressionUrl) {
            this.expressionUrl = expressionUrl;
        }

        public int getCommentLocationType() {
            return commentLocationType;
        }

        public void setCommentLocationType(int commentLocationType) {
            this.commentLocationType = commentLocationType;
        }

        public int getParentCommentId() {
            return parentCommentId;
        }

        public void setParentCommentId(int parentCommentId) {
            this.parentCommentId = parentCommentId;
        }

        public Object getDecoration() {
            return decoration;
        }

        public void setDecoration(Object decoration) {
            this.decoration = decoration;
        }

        public Object getRepliedMark() {
            return repliedMark;
        }

        public void setRepliedMark(Object repliedMark) {
            this.repliedMark = repliedMark;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }


    }

    public static class UserBean {
        /**
         * locationInfo : null
         * liveInfo : null
         * anonym : 0
         * avatarUrl : http://p2.music.126.net/ubliTZpT_XEy5vzRiYFUKQ==/18797250790330390.jpg
         * authStatus : 0
         * experts : null
         * vipRights : {"associator":{"vipCode":100,"rights":true},"musicPackage":null,"redVipAnnualCount":1}
         * userId : 441846746
         * userType : 0
         * nickname : 哲也love
         * vipType : 11
         * remarkName : null
         * expertTags : null
         */

        private Object locationInfo;
        private Object liveInfo;
        private int anonym;
        private String avatarUrl;
        private int authStatus;
        private Object experts;
        private VipRightsBean vipRights;
        private int userId;
        private int userType;
        private String nickname;
        private int vipType;
        private Object remarkName;
        private Object expertTags;

        public Object getLocationInfo() {
            return locationInfo;
        }

        public void setLocationInfo(Object locationInfo) {
            this.locationInfo = locationInfo;
        }

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getAnonym() {
            return anonym;
        }

        public void setAnonym(int anonym) {
            this.anonym = anonym;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public Object getExperts() {
            return experts;
        }

        public void setExperts(Object experts) {
            this.experts = experts;
        }

        public VipRightsBean getVipRights() {
            return vipRights;
        }

        public void setVipRights(VipRightsBean vipRights) {
            this.vipRights = vipRights;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public static class VipRightsBean {
            /**
             * associator : {"vipCode":100,"rights":true}
             * musicPackage : null
             * redVipAnnualCount : 1
             */

            private AssociatorBean associator;
            private Object musicPackage;
            private int redVipAnnualCount;

            public AssociatorBean getAssociator() {
                return associator;
            }

            public void setAssociator(AssociatorBean associator) {
                this.associator = associator;
            }

            public Object getMusicPackage() {
                return musicPackage;
            }

            public void setMusicPackage(Object musicPackage) {
                this.musicPackage = musicPackage;
            }

            public int getRedVipAnnualCount() {
                return redVipAnnualCount;
            }

            public void setRedVipAnnualCount(int redVipAnnualCount) {
                this.redVipAnnualCount = redVipAnnualCount;
            }

            public static class AssociatorBean {
                /**
                 * vipCode : 100
                 * rights : true
                 */

                private int vipCode;
                private boolean rights;

                public int getVipCode() {
                    return vipCode;
                }

                public void setVipCode(int vipCode) {
                    this.vipCode = vipCode;
                }

                public boolean isRights() {
                    return rights;
                }

                public void setRights(boolean rights) {
                    this.rights = rights;
                }
            }
        }
    }
}
