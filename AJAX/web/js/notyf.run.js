(function () {
    var notyf = new Notyf({ delay: 3000 });
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById('get').addEventListener('click', function () {
            notyf.confirm("操作成功");
        });

        document.getElementById('alert-btn').addEventListener('click', function () {
            notyf.alert("操作失败");
        });
    });
})();