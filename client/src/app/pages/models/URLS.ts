export class URLS{
    //prod
    //public static cloud_provider = "https://server-cloud-heroku.herokuapp.com";
    //public static cloud_provider = "http://cloud-env.vmfc4stcsn.eu-west-1.elasticbeanstalk.com/";
    //dev
    public static cloud_provider = "http://ec2-52-214-70-14.eu-west-1.compute.amazonaws.com:8080";
    public static GET_CATEGORIES = URLS.cloud_provider +"/api/categories/";
    public static GET_SUB_CATEGORIES = URLS.cloud_provider + "/api/sub/categories/";
    public static GET_COUNTRIES = URLS.cloud_provider + "/api/countries/";
    public static GET_TOP_PROJECTS = URLS.cloud_provider + "/api/projects/";
    public static GET_TOTAL_PLEDGED = URLS.cloud_provider + "/api/category/total/pledge";
    public static GET_PLEDGED_STATE = URLS.cloud_provider + "/api/projects/state";
}