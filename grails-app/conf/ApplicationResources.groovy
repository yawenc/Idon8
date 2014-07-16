modules = {
    application {
        resource url:'js/application.js'
    }

    jqPlot {
        dependsOn('jquery')
        resource url: 'js/jqplot/excanvas.min.js', disposition: 'head'
        resource url: 'js/jqplot/jquery.jqplot.min.js', disposition: 'head'
        resource url: 'js/jqplot/jquery.jqplot.min.css', disposition: 'head'

        resource url: 'js/jqplot/plugins/jqplot.pieRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.donutRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.dateAxisRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.canvasTextRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.cursor.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js', disposition: 'head'
        resource url: 'js/jqplot/plugins/jqplot.bubbleRenderer.min.js', disposition: 'head'
    }
}