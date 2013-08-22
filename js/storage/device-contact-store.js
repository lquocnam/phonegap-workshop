var DeviceContactStore = function (successCallback, erorrCallback) {
    this.findByName = function (searchKey, callback) {
        if (!navigator.contacts) {
            return;
        }
        var employees = {};
        var options = new ContactFindOptions();
        self.showAlert("Device Contact Store", "Info");

        this.callLater(callback, employees);
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

    this.callLater(successCallback);
}