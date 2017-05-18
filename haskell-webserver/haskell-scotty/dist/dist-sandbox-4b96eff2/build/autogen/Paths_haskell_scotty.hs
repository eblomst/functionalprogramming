{-# LANGUAGE CPP #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
{-# OPTIONS_GHC -fno-warn-implicit-prelude #-}
module Paths_haskell_scotty (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/bin"
libdir     = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/lib/x86_64-osx-ghc-8.0.2/haskell-scotty-0.1.0.0-4jJFpz5Ko8QASlW1IqThBb"
dynlibdir  = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/lib/x86_64-osx-ghc-8.0.2"
datadir    = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/share/x86_64-osx-ghc-8.0.2/haskell-scotty-0.1.0.0"
libexecdir = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/libexec"
sysconfdir = "/Users/emmablomsterberg/NetBeansProjects/functionalprogramming/haskell-webserver/haskell-scotty/.cabal-sandbox/etc"

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "haskell_scotty_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "haskell_scotty_libdir") (\_ -> return libdir)
getDynLibDir = catchIO (getEnv "haskell_scotty_dynlibdir") (\_ -> return dynlibdir)
getDataDir = catchIO (getEnv "haskell_scotty_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "haskell_scotty_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "haskell_scotty_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
