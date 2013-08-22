var DeviceContactStore = function (successCallback, erorrCallback) {
    this.findByName = function (searchKey, callback) {
        if (!navigator.contacts) {
            return;
        }
        
        var options = new ContactFindOptions();
        options.filter = "R";
        var fields = ["givenName", "familyName", "work", "mobile"];
        navigator.contacts.find(fields, onSuccess, onFail, options);
        self.showAlert("Device Contact Store", "Info");
        this.callLater(callback, this.contacts);
    };

    var onSuccess = function (contacts) {
        this.contacts = contacts;
    };

    var onFail = function (contactError) {
        
    };

    // Used to simulate async calls. This is done to provide a consistent interface with stores (like WebSqlStore)
    // that use async data access APIs
    this.callLater = function (callback, data) {
        if (callback) {
            setTimeout(function () {
                callback(data);
            });
        }
    };

    this.contacts = [];

    this.callLater(successCallback);
}